package com.inspiracode.inspiraschool.dto.cat;

import java.util.Date;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public class Student implements BaseDTO {
  private int id;
  private String name;
  private String lastName;
  private Date birthDate;
  private String address;
  private String gender;
  private String enrollNumber;
  private Date enrollmentDate;
  private Company company;
  private Status status;
  private String clockId;

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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEnrollNumber() {
    return enrollNumber;
  }

  public void setEnrollNumber(String enrollNumber) {
    this.enrollNumber = enrollNumber;
  }

  public Date getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(Date enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getClockId() {
    return clockId;
  }

  public void setClockId(String clockId) {
    this.clockId = clockId;
  }

}
