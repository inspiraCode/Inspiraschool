package com.inspiracode.inspiraschool.dto.ctrl;

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
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

@Entity
@Table(name = "ctrl_score", catalog = "school_control")
public class Score implements BaseDTO {
  private static final long serialVersionUID = 2571963413301891188L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_score")
  private int id;

  @JoinColumn(name = "id_student")
  @ManyToOne(fetch = FetchType.LAZY)
  private Student student;

  @JoinColumn(name = "id_group_assignment")
  @ManyToOne(fetch = FetchType.LAZY)
  private GroupAssignment groupAssignment;

  @Column(name = "comments")
  private String comment;

  @Column(name = "partial_one")
  private int parcialOne;

  @Column(name = "partial_two")
  private int parcialTwo;

  @Column(name = "partial_three")
  private int parcialThree;

  @Column(name = "partial_four")
  private int parcialFour;

  @Column(name = "partial_five")
  private int parcialFive;

  @Column(name = "partial_six")
  private int parcialSix;

  @Column(name = "extraordinary")
  private int extraOrdinary;

  @Column(name = "extraordinary_two")
  private int extraOrdinaryTwo;

  @Column(name = "special")
  private int special;

  @Column(name = "final")
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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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
