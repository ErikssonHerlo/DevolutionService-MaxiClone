package com.erikssonherlo.devolution.domain.model;

import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import com.erikssonherlo.devolution.domain.model.DevolutionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Devolution {

    private Integer devolutionId;
    private Integer shipmentId;              // ID de envío
    private Integer storeId;                 // ID de tienda
    private String userEmail;                // Correo del usuario que reporta la incidencia
    private DevolutionStatus status;                   // Estado de la incidencia (OPEN, CLOSED)
    private LocalDateTime createdAt;         // Fecha de creación
    private LocalDateTime updatedAt;         // Fecha de última actualización
    private LocalDateTime deletedAt;         // Fecha de eliminación (para soft delete)
    private List<DevolutionDetail> details;    // Lista de detalles de la incidencia
}
