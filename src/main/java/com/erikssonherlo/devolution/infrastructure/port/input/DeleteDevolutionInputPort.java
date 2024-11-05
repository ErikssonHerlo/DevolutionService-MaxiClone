package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.domain.model.Devolution;

public interface DeleteDevolutionInputPort {
    Devolution deleteDevolution(Long id);
}
