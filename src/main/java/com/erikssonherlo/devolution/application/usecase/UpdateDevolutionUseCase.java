package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceAlreadyExistsException;
import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.common.application.security.JWTService;
import com.erikssonherlo.devolution.application.dto.CreateDevolutionDTO;
import com.erikssonherlo.devolution.application.dto.NotificationDTO;
import com.erikssonherlo.devolution.application.dto.UpdateDevolutionDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.domain.model.enums.TemplateType;
import com.erikssonherlo.devolution.infrastructure.port.input.UpdateDevolutionInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateDevolutionUseCase implements UpdateDevolutionInputPort {
    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;
    private final JWTService jwtService;
    private  final HttpServletRequest request;
    private final NotificationDTO notificationDTO = new NotificationDTO();
    private final RabbitTemplate rabbitTemplate;
    private final String queueName = "notifications";

    @Override
    public Devolution updateDevolution(Long id, UpdateDevolutionDTO updateDevolutionDTO) {
        try {
            String token = jwtService.extractToken(request);
            DevolutionStatus status = DevolutionStatus.valueOf(updateDevolutionDTO.status().toUpperCase());

            Optional<Devolution> devolution = devolutionRepositoryPort.find(id);
            if(devolution.isEmpty()) throw new ResourceNotFoundException("devolution","id",id);
            devolution.get().setStatus(status);

            notificationDTO.setTo(devolution.get().getUserEmail());
            notificationDTO.setSubject("YOUR DEVOLUTION #" + devolution.get().getDevolutionId() + " WAS UPDATED");
            notificationDTO.setTemplate(TemplateType.DEVOLUTION_CREATED);
            notificationDTO.setMessage(status.toString());
            //rabbitTemplate.convertAndSend(queueName, notificationDTO);
            System.out.println("Email enviado a la cola: " + notificationDTO);
            return devolutionRepositoryPort.updateDevolution(id, devolution.get());
        }catch (Exception e){
            throw e;
        }
    }
}
