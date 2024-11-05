package com.erikssonherlo.devolution.application.dto;

import com.erikssonherlo.devolution.domain.model.enums.DevolutionStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateDevolutionDTO(
      /*  @Email(message = "User email must be valid")
        @NotBlank(message = "User email is mandatory")
        String userEmail,*/

        DevolutionStatus status // Nullable, to update only if necessary

) {}
