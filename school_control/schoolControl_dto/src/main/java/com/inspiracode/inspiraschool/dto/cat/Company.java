package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_company", catalog = "school_control")
public class Company implements BaseDTO {
  private static final long serialVersionUID = -9083057173875045929L;
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
