package com.erikssonherlo.devolution.application.dto;

import java.math.BigDecimal;

public record DamagedProductReportDTO(
        String productSku,
        Integer affectedQuantity,
        String reason,
        Double unitCost,
        Double totalCost,
        Integer storeId
) {}
