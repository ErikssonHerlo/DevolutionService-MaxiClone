package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.port.input.GetAllDevolutionsInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllDevolutionsUseCase implements GetAllDevolutionsInputPort {
    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;

    @Override
    public Page<Devolution> getAllDevolutions(Pageable pageable, List<Long> storeIds, String status) {
        try {
            if (storeIds != null && !storeIds.isEmpty()) {
                if (status != null && !status.isEmpty()) {
                    DevolutionStatus devolutionStatus = DevolutionStatus.valueOf(status.toUpperCase());
                    return devolutionRepositoryPort.findAllDevolutionsByStoreIdAndStatus(pageable, storeIds, devolutionStatus);
                }
                return devolutionRepositoryPort.findAllDevolutionsByStoreId(pageable, storeIds);
            }

            if(status != null && !status.isEmpty()) {
                DevolutionStatus devolutionStatus = DevolutionStatus.valueOf(status.toUpperCase());
                return devolutionRepositoryPort.findAllDevolutionsByStatus(devolutionStatus, pageable);
            }

            return devolutionRepositoryPort.findAllDevolutions(pageable);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", DevolutionStatus.names()));
        }
    }
}
