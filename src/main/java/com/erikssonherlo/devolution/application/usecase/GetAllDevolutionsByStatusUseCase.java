package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.port.input.GetAllDevolutionsByStatusInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetAllDevolutionsByStatusUseCase implements GetAllDevolutionsByStatusInputPort {
    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;

    @Override
    public Page<Devolution> getAllDevolutionsByStatus(String status, Pageable pageable) {
        try {
            DevolutionStatus devolutionStatus = DevolutionStatus.valueOf(status.toUpperCase());

            return devolutionRepositoryPort.findAllDevolutionsByStatus(devolutionStatus, pageable);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", DevolutionStatus.names()));
        }
    }
}
