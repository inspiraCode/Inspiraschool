package com.inspiracode.inspiraschool.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.inspiracode.inspiraschool.dto.BaseDTO;

@Entity
@Table(name = "cat_group", catalog = "school_control")
public class Group implements BaseDTO {
    private static final long serialVersionUID = -2797889685826034468L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id;

    @Column(name = "grade")
    private String grade;

    @Column(name = "mode")
    private String mode;

    @Column(name = "day_trip")
    private String dayTrip;

    @JoinColumn(name = "id_career")
    @ManyToOne(fetch = FetchType.EAGER)
    private Career career;

    @JoinColumn(name = "id_period")
    @ManyToOne(fetch = FetchType.EAGER)
    private Period period;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getGrade() {
	return grade;
    }

    public void setGrade(String grade) {
	this.grade = grade;
    }

    public String getMode() {
	return mode;
    }

    public void setMode(String mode) {
	this.mode = mode;
    }

    public String getDayTrip() {
	return dayTrip;
    }

    public void setDayTrip(String dayTrip) {
	this.dayTrip = dayTrip;
    }

    public Career getCareer() {
	return career;
    }

    public void setCareer(Career career) {
	this.career = career;
    }

    public Period getPeriod() {
	return period;
    }

    public void setPeriod(Period period) {
	this.period = period;
    }

}
