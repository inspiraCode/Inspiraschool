package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;

@ManagedBean
@SessionScoped
public class SieGroupBean extends BaseFacesBean<SieGroup> {
    private static final long serialVersionUID = 7549788216996264705L;
    private static final Logger logger = Logger.getLogger(SieGroupBean.class.getName());
    @ManagedProperty("#{sieGroupService}")
    private SieGroupService sieGroupService;
    @ManagedProperty("#{periodService}")
    private PeriodService periodService;
    private int periodId;
    private int rowIndex = 1;
    private List<Student> sieStudents = new ArrayList<Student>();

    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }

    public SieGroupBean() {
	super(SieGroup.class);
    }

    public int sieScorePartialOne(Student studentItem, Assignment assignmentItem) {
	//recorrer las calificaciones del estudiante(studentItem)
	for (Score score : studentItem.getScores()) {//para cada calificacion 
	    //revisar si la assignatura del score es igual al parametro de assignatura (assignmentItem)
	    if (score.getGroupAssignment().getAssignment().equals(assignmentItem)) {
		//si es igual, devolver esa calificacion
		return score.getParcialOne()/10;
	    }
	}
	//si no se encontro, devolver 0.
	return 0;
    }

    public SieGroupService getSieGroupService() {
	return sieGroupService;
    }

    public void setSieGroupService(SieGroupService sieGroupService) {
	super.setService((BaseService<SieGroup>) sieGroupService);
	this.sieGroupService = sieGroupService;
    }

    public int getPeriodId() {
	return periodId;
    }

    public List<Student> getSieStudents() {
	rowIndex = 1;
	SieGroup sGroupWScores = sieGroupService.getSieGroupWithScores(getSelectedItem().getId());
	sieStudents.clear();
	if (sGroupWScores != null)
	    sieStudents.addAll(sGroupWScores.getStudents());
	//organizar la lista por Nombre
	Collections.sort(sieStudents);
	//Collections.sort(result, Student.Comparators.ENROLLMENT);
	return sieStudents;
    }

    public List<Assignment> getAssignments() {
	List<Assignment> result = new ArrayList<Assignment>();
	if (sieStudents.isEmpty())
	    getSieStudents();
	logger.debug("studiantes encontrados: " + sieStudents.size());
	//llenar la lista, y recorrer todos los estudiantes calificados
	for (Student sieStudent : sieStudents) {
	    logger.debug("calificaciones encontradas: " + sieStudent.getScores().size());
	    for (Score score : sieStudent.getScores()) {
		//score.getGroupAssignment().getAssignment();
		if (!result.contains(score.getGroupAssignment().getAssignment()))
		    result.add(score.getGroupAssignment().getAssignment());
	    }
	}
	logger.debug("materias encontradas: " + result.size());
	//Collections.sort(result);
	return result;
    }

    public String partialOne(SieGroup item) {
	setSelectedItem(item);
	return "Partial_one";
    }

    public String partialTwo(SieGroup item) {
	setSelectedItem(item);
	return "Partial_two";
    }

    public void setPeriodId(int periodId) {
	this.periodId = periodId;
	Period selectedPeriod = periodService.get(periodId);
	getSelectedItem().setPeriod(selectedPeriod);
    }

    public PeriodService getPeriodService() {
	return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
	this.periodService = periodService;
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
}
