package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.application.dto.CreateDevolutionDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;

public interface CreateDevolutionInputPort {
    Devolution createDevolution(CreateDevolutionDTO createDevolutionDto);
}
