package com.inspiracode.inspiraschool.dto.cat;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public class Status implements BaseDTO {
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
