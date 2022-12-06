package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.entities.enums.RoleEnums;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

   private Integer age;

   @Column(name = "full_name", nullable = false)
   private String fullName;

   @Enumerated(EnumType.STRING)
   private LevelEnum level;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private String username;

   @ManyToMany
   private Set<Role> roles;

   public User() {
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public LevelEnum getLevel() {
      return level;
   }

   public void setLevel(LevelEnum level) {
      this.level = level;
   }
}
