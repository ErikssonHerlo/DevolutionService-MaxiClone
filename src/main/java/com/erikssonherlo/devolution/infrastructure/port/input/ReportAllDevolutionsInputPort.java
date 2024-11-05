package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;

import java.util.List;

public interface ReportAllDevolutionsInputPort {
    List<Devolution> reportAllDevolutions(List<Long> storeIds, String status, String startDate, String endDate);
}
