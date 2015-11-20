package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_assignment", catalog = "school_control")
public class Assignment implements BaseDTO {
  private static final long serialVersionUID = -1404012876344978431L;
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
