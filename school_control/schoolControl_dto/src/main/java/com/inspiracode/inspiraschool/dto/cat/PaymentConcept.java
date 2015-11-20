package com.inspiracode.inspiraschool.dto.cat;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_payment_consept", catalog = "school_control")
public class PaymentConcept implements BaseDTO {
  private static final long serialVersionUID = 3669306078191407113L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_payment_consept")
  private int id;

  @Column(name = "concept")
  private String concept;

  @Column(name = "price")
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
