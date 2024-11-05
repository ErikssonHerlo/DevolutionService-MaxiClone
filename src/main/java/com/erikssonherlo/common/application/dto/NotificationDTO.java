package com.erikssonherlo.common.application.dto;

import com.erikssonherlo.common.domain.enums.TemplateType;
import jakarta.validation.constraints.NotNull;


public record NotificationDTO(
        @NotNull(message = "Subject is mandatory")
        String subject,

        @NotNull(message = "To mandatory")
        String to,

        @NotNull(message = "Message is mandatory")
        String message,

        @NotNull(message = "Template is mandatory")
        TemplateType template
) {
}
