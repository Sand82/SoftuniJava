package com.example.lazyeagerloading.repositories;

import com.example.lazyeagerloading.models.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles")
    List<UserEntity> getUsersByFetch();

    //for entity graph
    @EntityGraph(value = "user-roles")
    @Query("SELECT u FROM UserEntity  u")
    List<UserEntity> getUsersByEntityGraph();
}
