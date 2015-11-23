package com.inspiracode.inspiraschool.jsf.beans.model.ctrl;

import com.inspiracode.inspiraschool.dto.ctrl.Score;

public class ScoreModel extends Score {
    private static final long serialVersionUID = 1L;

    private String studentName;
    private String studentEnrollment;

    private String assignmentName;
    private String groupName;

    private int index;

    public ScoreModel(Score score) {
	this.setComment(score.getComment());
	this.setFinalScore(score.getFinalScore());
	this.setId(score.getId());
	this.setParcialOne(score.getParcialOne());
	this.setParcialTwo(score.getParcialTwo());
    }

    public String getStudentName() {
	return studentName;
    }

    public void setStudentName(String studentName) {
	this.studentName = studentName;
    }

    public String getStudentEnrollment() {
	return studentEnrollment;
    }

    public void setStudentEnrollment(String studentEnrollment) {
	this.studentEnrollment = studentEnrollment;
    }

    public String getAssignmentName() {
	return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
	this.assignmentName = assignmentName;
    }

    public String getGroupName() {
	return groupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
    }

    public int getIndex() {
	return index;
    }

    public void setIndex(int index) {
	this.index = index;
    }
}