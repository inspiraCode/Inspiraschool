package com.inspiracode.inspiraschool.dto.cat;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_sie_group", catalog = "school_control")
public class SieGroup implements BaseDTO {
    private static final long serialVersionUID = -185712408958712867L;

    @Id
    @GeneratedValue
    @Column(name = "cat_sie_group_id")
    private int id;

    @Column(name = "cat_sie_group_name")
    private String sieGroupName;

    @JoinColumn(name = "id_period", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Period period;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cross_sie_student", catalog = "school_control", joinColumns = { @JoinColumn(name = "cat_sie_group_id") },
	    inverseJoinColumns = { @JoinColumn(name = "id_student") })
    private Set<Student> students = new HashSet<Student>();
    
    
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getSieGroupName() {
	return sieGroupName;
    }

    public void setSieGroupName(String sieGroupName) {
	this.sieGroupName = sieGroupName;
    }

    public Period getPeriod() {
	return period;
    }

    public void setPeriod(Period period) {
	this.period = period;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof SieGroup))
	    return false;
	SieGroup temp = (SieGroup) obj;
	return temp.id == this.id;
    }
    
    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + sieGroupName).hashCode();
	return tmp;
    }
    
    @Override
    public String toString() {
        return "id:" + id + ";sieGroupName:" + sieGroupName + ";period:{" + period + "}";
    }

    public Set<Student> getStudents() {
	return students;
    }

    public void setStudents(Set<Student> students) {
	this.students = students;
    }

}