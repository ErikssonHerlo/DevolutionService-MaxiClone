package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.repository;

import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevolutionJpaRepository extends JpaRepository<DevolutionEntity, Long> {

    // Encuentra devolutiones por estado de forma paginada
    Page<DevolutionEntity> findByStatus(DevolutionStatus status, Pageable pageable);

    Page<DevolutionEntity> findByStoreIdIn(List<Long> storeIds, Pageable pageable);

    Page<DevolutionEntity> findAllByStoreIdInAndStatus(List<Long> storeIds, DevolutionStatus status, Pageable pageable);
}
