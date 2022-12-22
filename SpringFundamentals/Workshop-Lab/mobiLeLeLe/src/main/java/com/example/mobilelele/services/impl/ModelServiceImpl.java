package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.repositories.ModelRepository;
import com.example.mobilelele.services.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelEntity getByName(String model) {

        return modelRepository.findByName(model);
    }
}
