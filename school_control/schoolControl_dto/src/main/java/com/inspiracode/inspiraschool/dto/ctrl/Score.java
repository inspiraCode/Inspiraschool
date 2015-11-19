package com.inspiracode.inspiraschool.dto.ctrl;

import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public class Score implements BaseDTO {
  private int id;
  private Student student;
  private GroupAssignment groupAssignment;
  private String coment;
  private int parcialOne;
  private int parcialTwo;
  private int parcialThree;
  private int parcialFour;
  private int parcialFive;
  private int parcialSix;
  private int extraOrdinary;
  private int extraOrdinaryTwo;
  private int special;
  private int finalScore;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public GroupAssignment getGroupAssignment() {
    return groupAssignment;
  }

  public void setGroupAssignment(GroupAssignment groupAssignment) {
    this.groupAssignment = groupAssignment;
  }

  public String getComent() {
    return coment;
  }

  public void setComent(String coment) {
    this.coment = coment;
  }

  public int getParcialOne() {
    return parcialOne;
  }

  public void setParcialOne(int parcialOne) {
    this.parcialOne = parcialOne;
  }

  public int getParcialTwo() {
    return parcialTwo;
  }

  public void setParcialTwo(int parcialTwo) {
    this.parcialTwo = parcialTwo;
  }

  public int getParcialThree() {
    return parcialThree;
  }

  public void setParcialThree(int parcialThree) {
    this.parcialThree = parcialThree;
  }

  public int getParcialFour() {
    return parcialFour;
  }

  public void setParcialFour(int parcialFour) {
    this.parcialFour = parcialFour;
  }

  public int getParcialFive() {
    return parcialFive;
  }

  public void setParcialFive(int parcialFive) {
    this.parcialFive = parcialFive;
  }

  public int getParcialSix() {
    return parcialSix;
  }

  public void setParcialSix(int parcialSix) {
    this.parcialSix = parcialSix;
  }

  public int getExtraOrdinary() {
    return extraOrdinary;
  }

  public void setExtraOrdinary(int extraOrdinary) {
    this.extraOrdinary = extraOrdinary;
  }

  public int getExtraOrdinaryTwo() {
    return extraOrdinaryTwo;
  }

  public void setExtraOrdinaryTwo(int extraOrdinaryTwo) {
    this.extraOrdinaryTwo = extraOrdinaryTwo;
  }

  public int getSpecial() {
    return special;
  }

  public void setSpecial(int special) {
    this.special = special;
  }

  public int getFinalScore() {
    return finalScore;
  }

  public void setFinalScore(int finalScore) {
    this.finalScore = finalScore;
  }

}
