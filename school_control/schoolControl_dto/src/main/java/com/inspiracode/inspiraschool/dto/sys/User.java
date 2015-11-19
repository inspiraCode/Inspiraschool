package com.inspiracode.inspiraschool.dto.sys;

import java.util.HashSet;
import java.util.Set;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public class User implements BaseDTO {
  private int id;
  private String userName;
  private String pass;
  private boolean enable;
  private int failLogIn;
  private String firtName;
  private String lastName;
  private String email;
  private Set<Role> roles = new HashSet<Role>(); 

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public int getFailLogIn() {
    return failLogIn;
  }

  public void setFailLogIn(int failLogIn) {
    this.failLogIn = failLogIn;
  }

  public String getFirtName() {
    return firtName;
  }

  public void setFirtName(String firtName) {
    this.firtName = firtName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

}
