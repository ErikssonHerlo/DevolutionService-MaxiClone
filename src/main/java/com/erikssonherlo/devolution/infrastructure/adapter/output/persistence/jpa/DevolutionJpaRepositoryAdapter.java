package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.jpa;

import com.erikssonherlo.devolution.application.dto.DamagedProductReportDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.domain.model.DevolutionDetail;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionDetailEntity;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionEntity;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.repository.DevolutionJpaRepository;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DevolutionJpaRepositoryAdapter implements DevolutionJpaRepositoryPort {

    private final DevolutionJpaRepository devolutionJpaRepository;

    @Override
    public Optional<Devolution> find(Long id) {
        return devolutionJpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<DevolutionEntity> findEntity(Long id) {
        return devolutionJpaRepository.findById(id);
    }

    @Override
    public Boolean existsDevolution(Long id) {
        return devolutionJpaRepository.existsById(id);
    }

    @Override
    public Page<Devolution> findAllDevolutions(Pageable pageable) {
        return devolutionJpaRepository.findAll(pageable).map(this::mapToDomain);
    }

    @Override
    public Page<Devolution> findAllDevolutionsByStatus(DevolutionStatus status, Pageable pageable) {
        return devolutionJpaRepository.findByStatus(status, pageable).map(this::mapToDomain);
    }

    @Override
    public Page<Devolution> findAllDevolutionsByStoreIdAndStatus(Pageable pageable, List<Long> storeIds, DevolutionStatus status) {
        return devolutionJpaRepository.findAllByStoreIdInAndStatus(storeIds, status, pageable)
                .map(this::mapToDomain);
    }

    // Method to get devolutions by store IDs (without status filtering)
    @Override
    public Page<Devolution> findAllDevolutionsByStoreId(Pageable pageable, List<Long> storeIds) {
        return devolutionJpaRepository.findByStoreIdIn(storeIds, pageable)
                .map(this::mapToDomain);
    }

    @Override
    public List<Devolution> reportAllDevolutions(List<Long> storeIds, DevolutionStatus status, String startDate, String endDate) {

        return devolutionJpaRepository.findAllByStoreIdInAndStatusAndCreatedAtBetween(storeIds, status.toString(), startDate, endDate).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DamagedProductReportDTO> findDamagedProductsReportByStoreAndDateRange(Long storeId, LocalDateTime startDate, LocalDateTime endDate) {
        return devolutionJpaRepository.findDamagedProductsReportByStoreAndDateRange(storeId, startDate, endDate);
    }

    @Override
    public Devolution saveDevolution(Devolution devolution) {
        DevolutionEntity devolutionEntity = mapToEntity(devolution, false);
        DevolutionEntity savedEntity = devolutionJpaRepository.save(devolutionEntity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Devolution updateDevolution(Long id, Devolution devolution) {
        if (existsDevolution(id)) {
            DevolutionEntity devolutionEntity = mapToEntity(devolution, true);
            devolutionEntity.setDevolutionId(id.intValue());  // Ensure the ID remains consistent

            DevolutionEntity updatedEntity = devolutionJpaRepository.save(devolutionEntity);
            return mapToDomain(updatedEntity);
        } else {
            throw new IllegalArgumentException("Devolution with id " + id + " does not exist.");
        }
    }

    @Override
    public void deleteDevolution(Long id) {
        devolutionJpaRepository.deleteById(id);
    }

    // Helper methods for mapping

    private Devolution mapToDomain(DevolutionEntity entity) {
        List<DevolutionDetail> details = entity.getDevolutionDetails().stream()
                .map(detailEntity -> DevolutionDetail.builder()
                        .devolutionDetailId(detailEntity.getId())
                        .devolutionId(detailEntity.getDevolution().getDevolutionId())
                        .productSku(detailEntity.getProductSku())
                        .affectedQuantity(detailEntity.getAffectedQuantity())
                        .reason(detailEntity.getReason())
                        .isDamaged(detailEntity.getIsDamaged())
                        .unitCost(detailEntity.getUnitCost())
                        .totalCost(detailEntity.getTotalCost())
                        .createdAt(detailEntity.getCreatedAt())
                        .updatedAt(detailEntity.getUpdatedAt())
                        .deletedAt(detailEntity.getDeletedAt())
                        .build())
                .collect(Collectors.toList());

        return Devolution.builder()
                .devolutionId(entity.getDevolutionId())
                .shipmentId(entity.getShipmentId())
                .storeId(entity.getStoreId())
                .userEmail(entity.getUserEmail())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .details(details)
                .build();
    }

    private DevolutionEntity mapToEntity(Devolution devolution, boolean isUpdate) {
        DevolutionEntity entity = DevolutionEntity.builder()
                .shipmentId(devolution.getShipmentId())
                .storeId(devolution.getStoreId())
                .userEmail(devolution.getUserEmail())
                .status(devolution.getStatus())
                .createdAt(devolution.getCreatedAt())
                .updatedAt(devolution.getUpdatedAt())
                .deletedAt(devolution.getDeletedAt())
                .build();

        if (!isUpdate && devolution.getDetails() != null) {
            List<DevolutionDetailEntity> detailEntities = devolution.getDetails().stream()
                .map(detail -> DevolutionDetailEntity.builder()
                        .devolution(entity)
                        .productSku(detail.getProductSku())
                        .affectedQuantity(detail.getAffectedQuantity())
                        .reason(detail.getReason())
                        .isDamaged(detail.getIsDamaged())
                        .unitCost(detail.getUnitCost())
                        .totalCost(detail.getTotalCost())
                        .createdAt(detail.getCreatedAt())
                        .updatedAt(detail.getUpdatedAt())
                        .deletedAt(detail.getDeletedAt())
                        .build())
                .collect(Collectors.toList());
            entity.setDevolutionDetails(detailEntities);
        } else {
            entity.setDevolutionDetails(new ArrayList<>());
        }
        return entity;
    }

}
