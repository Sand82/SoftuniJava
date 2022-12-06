package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.RoleEnums;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnums role;

    public Role() {
    }

    public RoleEnums getRole() {
        return role;
    }

    public void setRole(RoleEnums role) {
        this.role = role;
    }
}
