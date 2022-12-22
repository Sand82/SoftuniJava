package com.example.mobilelele.repositories;

import com.example.mobilelele.model.entities.BrandEntity;

import com.example.mobilelele.model.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findAllDistinctBy();
}
