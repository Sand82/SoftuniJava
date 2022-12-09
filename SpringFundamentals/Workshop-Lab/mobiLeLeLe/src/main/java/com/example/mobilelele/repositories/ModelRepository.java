package com.example.mobilelele.repositories;


import com.example.mobilelele.model.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    ModelEntity findByName(String fiesta);
}
