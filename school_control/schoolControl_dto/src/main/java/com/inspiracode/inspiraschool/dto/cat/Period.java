package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_period", catalog = "school_control")
public class Period implements BaseDTO {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    private int id;

    @Column(name = "period_name")
    private String periodName;

    @Column(name = "period_year")
    private int periodYear;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getPeriodName() {
	return periodName;
    }

    public void setPeriodName(String periodName) {
	this.periodName = periodName;
    }

    public int getPeriodYear() {
	return periodYear;
    }

    public void setPeriodYear(int periodYear) {
	this.periodYear = periodYear;
    }

}
