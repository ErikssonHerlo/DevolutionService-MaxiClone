package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.port.input.ReportAllDevolutionsInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportAllDevolutionsUseCase implements ReportAllDevolutionsInputPort {
    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;

    @Override
    public List<Devolution> reportAllDevolutions(List<Long> storeIds, String status, String startDate, String endDate) {
        try {
            DevolutionStatus devolutionStatus = DevolutionStatus.valueOf(status.toUpperCase());

            System.out.println("storeIds: " + storeIds);
            System.out.println("status: " + devolutionStatus);
            System.out.println("startDate: " + startDate);
            System.out.println("endDate: " + endDate);
            return devolutionRepositoryPort.reportAllDevolutions(storeIds, devolutionStatus, startDate, endDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: "
                    + String.join(", ", DevolutionStatus.names()));
        }
    }
}
