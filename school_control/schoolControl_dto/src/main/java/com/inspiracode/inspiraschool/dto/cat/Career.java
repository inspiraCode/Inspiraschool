package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_career", catalog = "school_control")
public class Career implements BaseDTO {
  private static final long serialVersionUID = -8941030536835637271L;
  private int id;
  private String name;
  private String code;

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
