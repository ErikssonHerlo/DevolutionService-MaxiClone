package com.erikssonherlo.devolution.application.dto;

import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateDevolutionDTO(
        @NotNull(message = "Shipment ID is mandatory")
        Integer shipmentId,

        @NotNull(message = "Store ID is mandatory")
        Integer storeId,

        @Email(message = "User email must be valid")
        @NotBlank(message = "User email is mandatory")
        String userEmail,

        @NotNull(message = "Status is mandatory")
        DevolutionStatus status,

        @NotEmpty(message = "Devolution details cannot be empty")
        List<CreateDevolutionDetailDTO> details // List of detail records
) {}
