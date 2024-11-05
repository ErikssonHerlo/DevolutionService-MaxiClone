package com.erikssonherlo.devolution.application.dto;
import com.erikssonherlo.devolution.domain.model.enums.TemplateType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    @NotNull(message = "Subject is mandatory")
    private String subject;
    @NotNull(message = "To mandatory")
    private String to;
    @NotNull(message = "Message is mandatory")
    private String message;
    @NotNull(message = "Template is mandatory")
    private TemplateType template;
}