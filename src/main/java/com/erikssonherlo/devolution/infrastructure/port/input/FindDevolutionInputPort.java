package com.erikssonherlo.devolution.infrastructure.port.input;

import com.erikssonherlo.devolution.domain.model.Devolution;

public interface FindDevolutionInputPort {
    Devolution findDevolution(Long id);
}
