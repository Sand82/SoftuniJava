package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.entities.Product;
import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.products.ProductInRangeDTO;
import com.example.jsonconvert.productshop.entities.products.ProductsInRangeDTO;
import com.example.jsonconvert.productshop.repositories.ProductRepository;
import com.example.jsonconvert.productshop.services.ProductService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final TypeMap<Product, ProductInRangeDTO> productToDtoMapping;
    private ProductRepository productRepository;

    private ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter = context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductInRangeDTO> typeMap = this.mapper.createTypeMap(Product.class, ProductInRangeDTO.class);

        this.productToDtoMapping = typeMap.addMappings(map -> map.using(userToFullNameConverter).map(Product::getSeller, ProductInRangeDTO::setSeller));
    }

    @Override
    public ProductsInRangeDTO getInRange(int lowerValue, int higherValue) {

        BigDecimal lowerBound = BigDecimal.valueOf(lowerValue);
        BigDecimal higherBound = BigDecimal.valueOf(higherValue);

        List<Product> products = productRepository.findByPriceBetweenAndBuyerIdNullOrderByPriceDesc(lowerBound, higherBound);

        List<ProductInRangeDTO> models = products.stream()
                .map(productToDtoMapping::map).collect(Collectors.toList());


        return new ProductsInRangeDTO(models);
    }
}
