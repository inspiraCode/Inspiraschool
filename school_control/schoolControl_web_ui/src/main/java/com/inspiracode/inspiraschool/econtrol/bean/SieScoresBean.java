package com.inspiracode.inspiraschool.econtrol.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.econtrol.model.rep.StudentSieModel;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesReporteableBean;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;

@ManagedBean
@SessionScoped
public class SieScoresBean extends BaseFacesReporteableBean<StudentSieModel> {
    private static final long serialVersionUID = -3998481311666199836L;

    @ManagedProperty("#{sieGroupService}")
    private SieGroupService sieGroupService;

    public SieScoresBean() {
	super(StudentSieModel.class);
    }

    @Override
    protected boolean validate() {
	return false;
    }

    public List<StudentSieModel> getSieGroupReportList(int groupId) {
	List<StudentSieModel> result = new ArrayList<StudentSieModel>();
	SieGroup sGroupWScores = sieGroupService.getSieGroupWithScores(groupId);
	if (sGroupWScores != null) {
	    for (Student student : sGroupWScores.getStudents()) {
		if (student.getStudentStatus().getId() == 1) {
		    for (Score score : student.getScores()) {
			int partialOne = score.getParcialOne() == null ? 0 : score.getParcialOne();
			int partialTwo = score.getParcialTwo() == null ? 0 : score.getParcialTwo();
			int finalScore = score.getFinalScore() == null ? 0 : score.getFinalScore();
			StudentSieModel model = new StudentSieModel(student, score.getGroupAssignment().getAssignment().getName(), partialOne, partialTwo,
				finalScore);
			result.add(model);
		    }
		}
	    }
	}
	//organizar la lista por Nombre
	Collections.sort(result);

	return result;
    }

    public SieGroupService getSieGroupService() {
	return sieGroupService;
    }

    public void setSieGroupService(SieGroupService sieGroupService) {
	this.sieGroupService = sieGroupService;
    }

}
