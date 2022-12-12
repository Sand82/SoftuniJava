package com.example.mobilelele.model.entities;


import com.example.mobilelele.model.entities.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserRoleEntity() {
    }

    public RoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }


}
