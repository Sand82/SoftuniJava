package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.entities.BrandEntity;
import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.model.view.BrandViewModel;
import com.example.mobilelele.model.view.ModelViewModel;
import com.example.mobilelele.repositories.BrandRepository;
import com.example.mobilelele.repositories.ModelRepository;

import com.example.mobilelele.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelRepository modelRepository;
    private ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper mapper) {

        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        List<ModelEntity> models = modelRepository.findAll();

        List<BrandEntity> brands = brandRepository.findAll();

        List<BrandViewModel> brandsDTOS =
                brands.stream()
                        .map(b -> new BrandViewModel().setName(b.getName())).toList();

        addModelsOnBrands(models, brandsDTOS);

        brandsDTOS = brandsDTOS.stream().filter(b -> b.getModels().size() > 0).toList();

        return brandsDTOS;
    }

    private void addModelsOnBrands(List<ModelEntity> models, List<BrandViewModel> brandsDTOS) {

        for (BrandViewModel brandsDTO : brandsDTOS) {

            List<ModelViewModel> modelsDTO = new ArrayList<>();

            for (ModelEntity model : models) {

                if (model.getBrand().getName().equals(brandsDTO.getName())) {

                    ModelViewModel modelDTO = mapper.map(model, ModelViewModel.class);
                    modelsDTO.add(modelDTO);
                }
            }
            brandsDTO.setModels(modelsDTO);
        }
    }
}
