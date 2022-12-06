package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.entities.enums.RoleEnums;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

   @Column(name = "full_name", nullable = false)
   private String fullName;

   @Column(name = "user_name", nullable = false)
   private String userName;

   private Integer age;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private String email;

   @ManyToMany
   private Set<Role> roles;

   @Enumerated(EnumType.STRING)
   private LevelEnum level;

   public User() {
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public String getUsername() {
      return fullName;
   }

   public void setUsername(String fullName) {
      this.fullName = fullName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
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
