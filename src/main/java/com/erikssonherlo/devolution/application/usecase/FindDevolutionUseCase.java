package com.erikssonherlo.devolution.application.usecase;

import com.erikssonherlo.common.application.exception.ResourceNotFoundException;
import com.erikssonherlo.devolution.domain.model.Devolution;
import com.erikssonherlo.devolution.infrastructure.port.input.FindDevolutionInputPort;
import com.erikssonherlo.devolution.infrastructure.port.output.db.DevolutionJpaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindDevolutionUseCase implements FindDevolutionInputPort {
    private final DevolutionJpaRepositoryPort devolutionRepositoryPort;

    @Override
    public Devolution findDevolution(Long id) throws ResourceNotFoundException {
        Optional<Devolution> devolution = devolutionRepositoryPort.find(id);
        if(devolution.isEmpty()) throw new ResourceNotFoundException("devolution","id",id);
        return devolution.get();
    }
}
