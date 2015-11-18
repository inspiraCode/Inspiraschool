package com.inspiracode.inspiraschool.dto.cat;

import java.math.BigDecimal;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public class PaymentConcept implements BaseDTO {
  private int id;
  private String concept;
  private BigDecimal price = new BigDecimal(0);

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getConcept() {
    return concept;
  }

  public void setConcept(String concept) {
    this.concept = concept;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
