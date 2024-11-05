package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity;

import com.erikssonherlo.common.application.anotation.PersistenceEntity;
import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity.DevolutionDetailEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devolution")
@SQLDelete(sql = "UPDATE devolution SET deleted_at = NOW() WHERE devolution_id = ?")
@Where(clause = "deleted_at IS NULL")
@PersistenceEntity
public class DevolutionEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "devolution_id")
        private Integer devolutionId;

        @Column(name = "shipment_id", nullable = false)
        private Integer shipmentId;

        @Column(name = "store_id", nullable = false)
        private Integer storeId;

        @Column(name = "user_email", nullable = false)
        private String userEmail;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private DevolutionStatus status;

        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Column(name = "deleted_at")
        private LocalDateTime deletedAt;

        @OneToMany(mappedBy = "devolution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<DevolutionDetailEntity> devolutionDetails;

        @PrePersist
        protected void onCreate() {
                this.createdAt = LocalDateTime.now();
                this.updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
                this.updatedAt = LocalDateTime.now();
        }
}
