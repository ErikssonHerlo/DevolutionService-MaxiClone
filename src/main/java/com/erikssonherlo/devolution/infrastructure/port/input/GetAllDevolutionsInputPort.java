package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.domain.model.Devolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetAllDevolutionsInputPort {
    Page<Devolution> getAllDevolutions(Pageable pageable, List<Long> storeId, String status);
}
