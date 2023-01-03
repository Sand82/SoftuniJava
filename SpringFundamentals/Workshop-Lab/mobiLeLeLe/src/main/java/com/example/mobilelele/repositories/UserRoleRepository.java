package com.example.mobilelele.repositories;

import com.example.mobilelele.model.entities.UserRoleEntity;
import com.example.mobilelele.model.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(RoleEnum role);
}
