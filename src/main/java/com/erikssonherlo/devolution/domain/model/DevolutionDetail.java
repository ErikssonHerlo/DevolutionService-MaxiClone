package com.erikssonherlo.devolution.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevolutionDetail {

    private Integer devolutionDetailId;        // ID del detalle de la incidencia
    private Integer devolutionId;              // ID de la incidencia (relación con Devolution)
    private String productSku;               // SKU del producto afectado
    private Integer affectedQuantity;        // Cantidad afectada
    private String reason;                   // Motivo de la incidencia
    private Boolean isDamaged;               // Indica si el producto está dañado
    private Double unitCost;                // Unit cost of the product
    private Double totalCost;               // Total cost of the product
    private LocalDateTime createdAt;         // Fecha de creación
    private LocalDateTime updatedAt;         // Fecha de última actualización
    private LocalDateTime deletedAt;         // Fecha de eliminación (para soft delete)
}
