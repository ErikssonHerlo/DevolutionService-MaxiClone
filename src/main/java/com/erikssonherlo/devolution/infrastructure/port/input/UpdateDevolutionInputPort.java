package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.application.dto.CreateDevolutionDTO;
import com.erikssonherlo.devolution.application.dto.UpdateDevolutionDTO;
import com.erikssonherlo.devolution.domain.model.Devolution;

public interface UpdateDevolutionInputPort {
    Devolution updateDevolution(Long id, UpdateDevolutionDTO updateDevolutionDTO);
}
