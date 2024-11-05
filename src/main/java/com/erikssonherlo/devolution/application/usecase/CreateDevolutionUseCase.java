package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceAlreadyExistsException;
import com.erikssonherlo.common.application.security.JWTService;
import com.erikssonherlo.devolution.application.dto.CreateDevolutionDTO;
import com.erikssonherlo.devolution.application.dto.NotificationDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.DevolutionDetail;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.domain.model.enums.TemplateType;
import com.erikssonherlo.devolution.infrastructure.port.input.CreateDevolutionInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateDevolutionUseCase implements CreateDevolutionInputPort {
    private final DevolutionJpaRepositoryPort devolutionJpaRepositoryPort;
    private final HttpServletRequest request;
    private final JWTService jwtService;
    private final NotificationDTO notificationDTO = new NotificationDTO();
    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "notifications";

    @Override
    public Devolution createDevolution(CreateDevolutionDTO createDevolutionDTO) {
        try {
            String token = jwtService.extractToken(request);

            DevolutionStatus status = DevolutionStatus.valueOf(createDevolutionDTO.status().toUpperCase());


                List<DevolutionDetail> details = createDevolutionDTO.details().stream()
                        .map(dto -> DevolutionDetail.builder()
                                .productSku(dto.productSku())
                                .affectedQuantity(dto.affectedQuantity())
                                .reason(dto.reason())
                                .isDamaged(dto.isDamaged())
                                .unitCost(dto.unitCost())
                                .totalCost(dto.totalCost())
                                .build())
                        .collect(Collectors.toList());

                Devolution devolution = Devolution.builder()
                        .shipmentId(createDevolutionDTO.shipmentId())
                        .storeId(createDevolutionDTO.storeId())
                        .userEmail(createDevolutionDTO.userEmail())
                        .status(status)
                        .details(details)
                        .build();

            Devolution devolutionCreated = devolutionJpaRepositoryPort.saveDevolution(devolution);

            notificationDTO.setTo(createDevolutionDTO.userEmail());
            notificationDTO.setSubject("NEW DEVOLUTION CREATED");
            notificationDTO.setTemplate(TemplateType.DEVOLUTION_CREATED);
            notificationDTO.setMessage(Long.toString(devolutionCreated.getDevolutionId()));
            rabbitTemplate.convertAndSend(queueName, notificationDTO);
            System.out.println("Email enviado a la cola: " + notificationDTO);

            return devolutionCreated;
        }catch (Exception e){
            throw e;
        }
    }
}
