package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.common.application.anotation.UseCase;
import com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO;
import com.erikssonherlo.devolution.infrastructure.port.input.ReportDamagedProductsInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@UseCase
@RequiredArgsConstructor

public class ReportDamagedProductsUseCase implements ReportDamagedProductsInputPort {

    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;

    @Override
    public List<DamagedProductReportDTO> getDamagedProductsReport(Long storeId, String startDate, String endDate) {
        LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate).atStartOfDay().plusDays(1);
        return devolutionRepositoryPort.findDamagedProductsReportByStoreAndDateRange(storeId, start, end);
    }
}
