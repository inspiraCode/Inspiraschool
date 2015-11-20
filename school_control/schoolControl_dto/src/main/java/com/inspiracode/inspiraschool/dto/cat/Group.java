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

  @Column(name = "period")
  private String period;

  @Column(name = "mode")
  private String mode;

  @Column(name = "day_trip")
  private String dayTrip;

  @Column(name = "year_of_course")
  private int year;

  @JoinColumn(name = "id_career")
  @ManyToOne(fetch=FetchType.LAZY)
  private Career career;

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

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
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

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Career getCareer() {
    return career;
  }

  public void setCareer(Career career) {
    this.career = career;
  }

}
