package com.erikssonherlo.devolution.infrastructure.adapter.output.persistence.entity;

import com.erikssonherlo.common.application.anotation.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devolution_detail")
@SQLDelete(sql = "UPDATE devolution_detail SET deleted_at = NOW() WHERE devolution_detail_id = ?")
@Where(clause = "deleted_at IS NULL")
@PersistenceEntity
public class DevolutionDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "devolution_detail_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devolution_id", nullable = false)
    private DevolutionEntity devolution;

    @Column(name = "product_sku", nullable = false)
    private String productSku;

    @Column(name = "affected_quantity", nullable = false)
    private Integer affectedQuantity;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "is_damaged", nullable = false)
    private Boolean isDamaged;

    @Column(name = "unit_cost", nullable = false)
    private Double unitCost;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

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
