package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.repository;

import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevolutionJpaRepository extends JpaRepository<DevolutionEntity, Long> {

    // Encuentra devolutiones por estado de forma paginada
    Page<DevolutionEntity> findByStatus(DevolutionStatus status, Pageable pageable);

    Page<DevolutionEntity> findByStoreIdIn(List<Long> storeIds, Pageable pageable);

    Page<DevolutionEntity> findAllByStoreIdInAndStatus(List<Long> storeIds, DevolutionStatus status, Pageable pageable);

    @Query(value = "SELECT * FROM devolution " +
            "WHERE store_id IN :storeIds " +
            "AND status = :status " +
            "AND created_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<DevolutionEntity> findAllByStoreIdInAndStatusAndCreatedAtBetween(
            @Param("storeIds") List<Long> storeIds,
            @Param("status") String status,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
