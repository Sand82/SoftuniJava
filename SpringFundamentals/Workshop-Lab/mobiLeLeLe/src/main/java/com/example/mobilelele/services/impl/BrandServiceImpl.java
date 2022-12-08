package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.view.BrandViewModel;
import com.example.mobilelele.repositories.BrandRepository;
import com.example.mobilelele.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {

        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        return brandRepository.findAllBrands();
    }
}
