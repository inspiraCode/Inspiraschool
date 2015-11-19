package com.inspiracode.inspiraschool.dto.cat;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public class Group implements BaseDTO {
  private int id;
  private String grade;
  private String period;
  private String mode;
  private String dayTrip;
  private int year;
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
