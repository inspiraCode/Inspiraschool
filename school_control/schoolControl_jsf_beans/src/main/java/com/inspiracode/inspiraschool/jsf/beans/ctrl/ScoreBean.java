package com.inspiracode.inspiraschool.jsf.beans.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cross.GroupAssignmentService;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;

@ManagedBean
@SessionScoped
public class ScoreBean extends BaseFacesBean<Score> {
    private static final long serialVersionUID = 1965513980898329854L;
    private static final Logger logger = Logger.getLogger(ScoreBean.class.getName());

    @ManagedProperty("#{scoreService}")
    private ScoreService scoreService;

    @ManagedProperty("#{groupAssignmentService}")
    private GroupAssignmentService groupService;

    public ScoreBean() {
	super(Score.class);
    }

    public List<Score> scoresByGroup(int idGroupAssignment) {
	List<Score> result = new ArrayList<Score>();
	// Cargar calificaciones existentes
	List<Score> existingScores = scoreService.scoresByGroup(idGroupAssignment);
	logger.debug(existingScores.size() + " existing scores in db.");
	if (existingScores != null && !existingScores.isEmpty()) {
	    for (Score score : existingScores) {
		Student studentInScore = scoreService.studentInScore(score.getId());
		Log.debug("Setting score student: " + studentInScore.getName());
		score.setStudent(studentInScore);
		result.add(score);
	    }
	}
	// Cargar materias asignadas sin calificar
	List<Student> studentsInGroup = groupService.getStudentsByGroupId(idGroupAssignment);
	logger.debug(studentsInGroup.size() + " students in group in db.");
	for (Student student : studentsInGroup) {
	    boolean found = false;
	    for (Score score : result) {

		Student studentInScore = scoreService.studentInScore(score.getId());
		if (studentInScore.getId() == student.getId()) {
		    found = true;
		    break;
		}
	    }

	    if (!found) {
		logger.debug("Adding student to group as no score was found:" + student.getName());
		Score newScore = new Score();
		newScore.setStudent(student);
		newScore.setGroupAssignment(groupService.get(idGroupAssignment));
		newScore.setParcialOne(0);
		newScore.setParcialTwo(0);

		result.add(newScore);
	    }
	}

	logger.debug(result.size() + " items to display in list");
	return result;
    }

    public ScoreService getScoreService() {
	return scoreService;
    }

    public void setScoreService(ScoreService scoreService) {
	super.setService((BaseService<Score>) scoreService);
	this.scoreService = scoreService;
    }

    public GroupAssignmentService getGroupService() {
	return groupService;
    }

    public void setGroupService(GroupAssignmentService groupService) {
	this.groupService = groupService;
    }

}
