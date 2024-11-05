package com.erikssonherlo.devolution.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDevolutionDetailDTO(
        @NotBlank(message = "Product SKU is mandatory")
        String productSku,

        @NotNull(message = "Affected quantity is mandatory")
        @Positive(message = "Affected quantity must be positive")
        Integer affectedQuantity,

        @NotBlank(message = "Reason is mandatory")
        String reason,

        @NotNull(message = "Is damaged is mandatory")
        Boolean isDamaged,

        @NotNull(message = "Unit cost is mandatory")
        @Positive(message = "Unit cost must be positive")
        Double unitCost,

        @NotNull(message = "Total cost is mandatory")
        @Positive(message = "Total cost must be positive")
        Double totalCost
) {}
