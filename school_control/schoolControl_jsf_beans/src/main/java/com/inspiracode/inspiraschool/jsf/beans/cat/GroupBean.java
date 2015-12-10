package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.dto.cat.Career;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesReporteableBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.CareerService;
import com.inspiracode.inspiraschool.service.cat.GroupService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;

@ManagedBean
@SessionScoped
public class GroupBean extends BaseFacesReporteableBean<Group> {
    private static final long serialVersionUID = -1169756132687652990L;
    private static final Logger logger = Logger.getLogger(GroupBean.class.getName());

    @ManagedProperty("#{groupService}")
    private GroupService groupService;

    @ManagedProperty("#{scoreService}")
    private ScoreService scoreService;

    @ManagedProperty("#{periodService}")
    private PeriodService periodService;
    @ManagedProperty("#{careerService}")
    private CareerService careerService;

    private int rowIndex = 1;
    private int selectedGroupId;
    private List<Assignment> cachedAssignments = new ArrayList<Assignment>();

    private List<Student> students = new ArrayList<Student>();

    public GroupBean() {
	super(Group.class);
    }

    public Map<String, Object> getScoresParams() {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("GROUP_ID", getSelectedItem().getId());

	params.put("USER_NAME", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
	return params;
    }

    public int getScore(Student studentItem, Assignment assignmentItem, int partialIndex) {
	logger.debug("Getting score for student: " + studentItem + " at assignment: " + assignmentItem);
	Score dbScore = scoreService.getScoreByStudentAndAssignment(studentItem.getId(), assignmentItem.getId());
	logger.debug("got score: " + dbScore);
	if (dbScore == null) {
	    return 0;
	}

	switch (partialIndex) {
	case 0:
	    return dbScore.getFinalScore() == null ? 0 : dbScore.getFinalScore();
	case 1:
	    return dbScore.getParcialOne() == null ? 0 : dbScore.getParcialOne();
	case 2:
	    return dbScore.getParcialTwo() == null ? 0 : dbScore.getParcialTwo();
	default:
	    return 0;
	}
    }

    public List<Student> getStudents() {
	rowIndex = 1;
	Group groupWithScores = groupService.getGroupWithStudents(getSelectedItem().getId());
	students.clear();
	for (GroupAssignment ga : groupWithScores.getAssignments()) {
	    for (Student st : ga.getStudents()) {
		if (!students.contains(st))
		    students.add(st);
	    }
	}
	Collections.sort(students);
	return students;
    }

    public List<Assignment> getAssignments() {
	if (selectedGroupId == getSelectedItem().getId()) {
	    return cachedAssignments;
	}

	List<Assignment> result = new ArrayList<Assignment>();
	Group groupWithAssignments = groupService.getGroupWithAssignments(getSelectedItem().getId());
	for (GroupAssignment ga : groupWithAssignments.getAssignments()) {
	    result.add(ga.getAssignment());
	}

	Collections.sort(result);
	cachedAssignments = result;
	selectedGroupId = groupWithAssignments.getId();
	return result;
    }

    public String partialOne(Group item) {
	setSelectedItem(item);
	return "Partial_one";
    }

    public String partialTwo(Group item) {
	setSelectedItem(item);
	return "Partial_two";
    }

    public String finalScore(Group item) {
	setSelectedItem(item);
	return "Final_score";
    }

    public GroupService getGroupService() {
	return groupService;
    }

    public void setGroupService(GroupService groupService) {
	super.setService((BaseService<Group>) groupService);
	this.groupService = groupService;
    }

    @Override
    protected boolean validate() {
	//TODO: Validate
	return true;
    }

    public int getPeriodId() {
	return getSelectedItem().getId() == 0 ? 0 : getSelectedItem().getPeriod().getId();
    }

    public void setPeriodId(int periodId) {
	Period selected = getPeriodService().get(periodId);
	getSelectedItem().setPeriod(selected);
    }

    public int getCareerId() {
	return getSelectedItem().getId() == 0 ? 0 : getSelectedItem().getCareer().getId();
    }

    public void setCareerId(int careerId) {
	Career selected = getCareerService().get(careerId);
	getSelectedItem().setCareer(selected);
    }

    public PeriodService getPeriodService() {
	return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
	this.periodService = periodService;
    }

    public CareerService getCareerService() {
	return careerService;
    }

    public void setCareerService(CareerService careerService) {
	this.careerService = careerService;
    }

    public int getSelectedGroupId() {
	return selectedGroupId;
    }

    public void setSelectedGroupId(int selectedGroupId) {
	this.selectedGroupId = selectedGroupId;
    }

    public int getRowIndex() {
	return rowIndex++;
    }

    public int getRowIndexed() {
	return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
	this.rowIndex = rowIndex;
    }

    public ScoreService getScoreService() {
	return scoreService;
    }

    public void setScoreService(ScoreService scoreService) {
	this.scoreService = scoreService;
    }

}
