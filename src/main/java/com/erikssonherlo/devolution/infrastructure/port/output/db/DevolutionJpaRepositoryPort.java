package com.erikssonherlo.devolution.infrastructure.port.output.db;

import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DevolutionJpaRepositoryPort {

    Optional<Devolution> find(Long id);

    Optional<DevolutionEntity> findEntity(Long id);

    Boolean existsDevolution (Long id);

    Page<Devolution> findAllDevolutions(Pageable pageable);

    Page<Devolution> findAllDevolutionsByStatus(DevolutionStatus status, Pageable pageable);

    Page<Devolution> findAllDevolutionsByStoreId(Pageable pageable, List<Long> storeIds);

    Page<Devolution> findAllDevolutionsByStoreIdAndStatus(Pageable pageable, List<Long> storeIds, DevolutionStatus status);

    Devolution saveDevolution(Devolution devolution);

    Devolution updateDevolution(Long id, Devolution devolution);

    void deleteDevolution(Long id);
}
