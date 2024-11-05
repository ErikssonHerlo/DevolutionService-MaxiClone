package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllDevolutionsByStatusInputPort {
    Page<Devolution> getAllDevolutionsByStatus(String status, Pageable pageable);
}
