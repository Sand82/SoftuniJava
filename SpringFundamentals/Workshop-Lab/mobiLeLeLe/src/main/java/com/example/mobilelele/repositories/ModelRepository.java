package com.example.mobilelele.repositories;

import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.model.view.AddModelViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    ModelEntity findByName(String name);

    List<ModelEntity> findAllDistinctBy();
}
