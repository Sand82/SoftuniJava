package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.RoleEnums;
import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnums name;

    public Role() {
    }

    public RoleEnums getName() {
        return name;
    }

    public void setName(RoleEnums name) {
        this.name = name;
    }


}
