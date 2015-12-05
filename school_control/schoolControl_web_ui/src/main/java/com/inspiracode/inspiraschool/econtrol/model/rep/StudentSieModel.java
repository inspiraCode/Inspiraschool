package com.inspiracode.inspiraschool.econtrol.model.rep;

import com.inspiracode.inspiraschool.dto.cat.Student;

public class StudentSieModel extends Student {
    private static final long serialVersionUID = -8122388222846758454L;

    private String assignmentName;
    private int assignmentScore;

    public int getAssignmentScore() {
	return assignmentScore;
    }

    public void setAssignmentScore(int assignmentScore) {
	this.assignmentScore = assignmentScore;
    }

    public String getAssignmentName() {
	return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
	this.assignmentName = assignmentName;
    }
}
