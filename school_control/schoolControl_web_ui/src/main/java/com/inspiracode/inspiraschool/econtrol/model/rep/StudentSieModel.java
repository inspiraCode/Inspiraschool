package com.inspiracode.inspiraschool.econtrol.model.rep;

import com.inspiracode.inspiraschool.dto.cat.Student;

public class StudentSieModel extends Student {
    private static final long serialVersionUID = -8122388222846758454L;

    private String assignmentName;
    private int partialOne;
    private int partialTwo;
    private int finalScore;

    public StudentSieModel(Student student, String assignmentName, int partialOne, int partialTwo, int finalScore) {
	this.setEnrollNumber(student.getEnrollNumber());
	this.setName(student.getName());
	this.setLastName(student.getLastName());

	this.setAssignmentName(assignmentName);
	this.setPartialOne(partialOne);
	this.setPartialTwo(partialTwo);
	this.setFinalScore(finalScore);
    }

    public String getAssignmentName() {
	return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
	this.assignmentName = assignmentName;
    }

    public int getPartialOne() {
	return partialOne;
    }

    public void setPartialOne(int partialOne) {
	this.partialOne = partialOne;
    }

    public int getPartialTwo() {
	return partialTwo;
    }

    public void setPartialTwo(int partialTwo) {
	this.partialTwo = partialTwo;
    }

    public int getFinalScore() {
	return finalScore;
    }

    public void setFinalScore(int finalScore) {
	this.finalScore = finalScore;
    }
}
