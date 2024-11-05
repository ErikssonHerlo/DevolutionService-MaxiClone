package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO;

import java.util.List;

public interface ReportDamagedProductsInputPort {

    List<DamagedProductReportDTO> getDamagedProductsReport(Long storeId, String startDate, String endDate);

}
