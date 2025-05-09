package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.repository;

import com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("SELECT new com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO(" +
            "dd.productSku, dd.affectedQuantity, dd.reason, dd.unitCost, dd.totalCost, d.storeId) " +
            "FROM DevolutionDetailEntity dd " +
            "JOIN dd.devolution d " +
            "WHERE d.storeId = :storeId " +
            "AND d.status = com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus.ACCEPTED " +
            "AND dd.isDamaged = true " +
            "AND dd.createdAt BETWEEN :startDate AND :endDate")
    List<DamagedProductReportDTO> findDamagedProductsReportByStoreAndDateRange(
            @Param("storeId") Long storeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);


}
