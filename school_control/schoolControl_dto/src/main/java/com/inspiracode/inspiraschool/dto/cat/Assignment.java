package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_assignment", catalog = "school_control")
public class Assignment implements BaseDTO {
    private static final long serialVersionUID = -1404012876344978431L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    private int id;

    @Column(name = "assignment_name")
    private String name;

    @Column(name = "sie_index")
    private int sieIndex;

    @Column(name = "sie_code")
    private String sieCode;

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

    public int getSieIndex() {
	return sieIndex;
    }

    public void setSieIndex(int sieIndex) {
	this.sieIndex = sieIndex;
    }

    public String getSieCode() {
	return sieCode;
    }

    public void setSieCode(String sieCode) {
	this.sieCode = sieCode;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof Assignment))
	    return false;
	Assignment o = (Assignment) obj;
	return this.getId() == o.getId();

    }

}
