package com.inspiracode.inspiraschool.dto.sys;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "sys_role", catalog = "inspiraschool_realm")
public class Role implements BaseDTO {
  private static final long serialVersionUID = -3093929431698566556L;
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
