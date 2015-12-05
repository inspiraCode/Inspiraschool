package com.inspiracode.inspiraschool.jsf.beans.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

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

    private int rowIndex = 1;
    private int loadedGroup = 0;

    public ScoreBean() {
	super(Score.class);
    }

    public List<Score> scoresByGroup(int idGroupAssignment) {
	rowIndex = 1;
	List<Score> result = new ArrayList<Score>();
	result.addAll(getUnsavedItems());
	if (idGroupAssignment != loadedGroup) {
	    result.clear();
	    // Cargar calificaciones existentes
	    List<Score> existingScores = scoreService.scoresByGroup(idGroupAssignment);
	    logger.debug(existingScores.size() + " existing scores in db.");
	    if (existingScores != null && !existingScores.isEmpty()) {
		result.addAll(existingScores);
	    }
	    // Cargar materias asignadas sin calificar
	    List<Student> studentsInGroup = groupService.getStudentsByGroupId(idGroupAssignment);
	    logger.debug(studentsInGroup.size() + " students in group in db.");
	    for (Student student : studentsInGroup) {
		boolean found = false;
		for (Score score : result) {
		    if (score.getId() == 0)
			continue;
		    if (score.getStudent().getId() == student.getId()) {
			found = true;
			break;
		    }
		}

		if (!found) {
		    Score newScore = new Score();
		    newScore.setStudent(student);
		    newScore.setGroupAssignment(groupService.get(idGroupAssignment));
		    result.add(newScore);
		}
	    }

	    logger.debug(result.size() + " items to display in list from career: " + result.get(0).getGroupAssignment().getGroup().getCareer().getName());
	    Collections.sort(result);
	    loadedGroup = idGroupAssignment;
	    setUnsavedItems(result);
	}
	return result;

    }

    public String calificar() {
	for (Score score : getUnsavedItems()) {
	    if (score.getParcialOne() == null && score.getParcialOne() == null && score.getComment() == null)
		continue;

	    if (score.getParcialOne() != null) {
		if (score.getParcialOne() > 10 || (score.getParcialOne() < 5 && score.getParcialOne() > 0)) {
		    publishWarning("Revise la calificación del parcial 1 para " + score.getStudent().getName());
		    return "";
		}
	    }

	    if (score.getParcialTwo() != null) {
		if ((score.getParcialTwo() < 5 && score.getParcialTwo() > 0) || score.getParcialTwo() > 10) {
		    publishWarning("Revise la calificación del parcial 2 para " + score.getStudent().getName());
		    return "";
		}
	    }
	    
	    if (score.getFinalScore() != null) {
		if ((score.getFinalScore() < 5 && score.getFinalScore() > 0) || score.getFinalScore() > 10) {
		    publishWarning("Revise la calificación para " + score.getStudent().getName());
		    return "";
		}
	    }

	    if (score.getId() == 0)
		getScoreService().add(score);
	    else
		getScoreService().update(score);
	}
	loadedGroup = 0;
	return "grupos";
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

    public int getRowIndex() {
	return rowIndex++;
    }

    public void setRowIndex(int rowIndex) {
	this.rowIndex = rowIndex;
    }

    public int getLoadedGroup() {
	return loadedGroup;
    }

    public void setLoadedGroup(int loadedGroup) {
	this.loadedGroup = loadedGroup;
    }

    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }

}
