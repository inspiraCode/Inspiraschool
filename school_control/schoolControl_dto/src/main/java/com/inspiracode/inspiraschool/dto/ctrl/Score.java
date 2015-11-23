package com.inspiracode.inspiraschool.dto.ctrl;

import java.util.Comparator;

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
public class Score implements BaseDTO, Comparable<Score> {
    private static final long serialVersionUID = 1L;

    public Score() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_score")
    private int id;

    @JoinColumn(name = "id_student")
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @JoinColumn(name = "id_group_assignment")
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupAssignment groupAssignment;

    @Column(name = "comments")
    private String comment;

    @Column(name = "partial_one")
    private Integer parcialOne;

    @Column(name = "partial_two")
    private Integer parcialTwo;

    @Column(name = "partial_three")
    private Integer parcialThree;

    @Column(name = "partial_four")
    private Integer parcialFour;

    @Column(name = "partial_five")
    private Integer parcialFive;

    @Column(name = "partial_six")
    private Integer parcialSix;

    @Column(name = "extraordinary", nullable = true)
    private Integer extraOrdinary;

    @Column(name = "extraordinary_two")
    private Integer extraOrdinaryTwo;

    @Column(name = "special")
    private Integer special;

    @Column(name = "final")
    private Integer finalScore;

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

    public Integer getParcialOne() {
	return parcialOne;
    }

    public void setParcialOne(Integer parcialOne) {
	this.parcialOne = parcialOne;
    }

    public Integer getParcialTwo() {
	return parcialTwo;
    }

    public void setParcialTwo(Integer parcialTwo) {
	this.parcialTwo = parcialTwo;
    }

    public Integer getParcialThree() {
	return parcialThree;
    }

    public void setParcialThree(Integer parcialThree) {
	this.parcialThree = parcialThree;
    }

    public Integer getParcialFour() {
	return parcialFour;
    }

    public void setParcialFour(Integer parcialFour) {
	this.parcialFour = parcialFour;
    }

    public Integer getParcialFive() {
	return parcialFive;
    }

    public void setParcialFive(Integer parcialFive) {
	this.parcialFive = parcialFive;
    }

    public Integer getParcialSix() {
	return parcialSix;
    }

    public void setParcialSix(Integer parcialSix) {
	this.parcialSix = parcialSix;
    }

    public Integer getExtraOrdinary() {
	return extraOrdinary;
    }

    public void setExtraOrdinary(Integer extraOrdinary) {
	this.extraOrdinary = extraOrdinary;
    }

    public Integer getExtraOrdinaryTwo() {
	return extraOrdinaryTwo;
    }

    public void setExtraOrdinaryTwo(Integer extraOrdinaryTwo) {
	this.extraOrdinaryTwo = extraOrdinaryTwo;
    }

    public Integer getSpecial() {
	return special;
    }

    public void setSpecial(Integer special) {
	this.special = special;
    }

    public Integer getFinalScore() {
	return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
	this.finalScore = finalScore;
    }

    @Override
    public int compareTo(Score o) {
	return Comparators.STUDENT_NAME.compare(this, o);
    }
    public static class Comparators {
	public static Comparator<Score> STUDENT_NAME = new Comparator<Score>() {

	    public int compare(Score s1, Score s2) {
		return s1.getStudent().getName().compareTo(s2.getStudent().getName());
	    }
	};
    }

}
