package com.inspiracode.inspiraschool.dto.cat;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_assignment", catalog = "school_control")
public class Assignment implements BaseDTO, Comparable<Assignment> {
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
    public String toString() {
	return "id:" + id + ";name:" + name + ";";
    }
    
    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + name).hashCode();
	return tmp;
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

    @Override
    public int compareTo(Assignment o) {
	return Comparators.INDEX.compare(this, o);
    }

    public static class Comparators {
	public static final Comparator<Assignment> INDEX = new Comparator<Assignment>() {

	    @Override
	    public int compare(Assignment o1, Assignment o2) {
		return o1.sieIndex - o2.sieIndex;
	    }
	};

	public static final Comparator<Assignment> NAME = new Comparator<Assignment>() {

	    @Override
	    public int compare(Assignment o1, Assignment o2) {
		return o1.getName().compareTo(o2.getName());
	    }
	};
    }

}
