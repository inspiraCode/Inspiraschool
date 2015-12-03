package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;

@ManagedBean
@SessionScoped
public class SieGroupBean extends BaseFacesBean<SieGroup> {
    private static final long serialVersionUID = 7549788216996264705L;

    @ManagedProperty("#{sieGroupService}")
    private SieGroupService sieGroupService;
    @ManagedProperty("#{periodService}")
    private PeriodService periodService;
    private int periodId;

    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }

    public SieGroupBean() {
	super(SieGroup.class);
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
	SieGroup sGroupWScores = sieGroupService.getSieGroupWithScores(getSelectedItem().getId());
	List<Student> result = new ArrayList<Student>();
	if (sGroupWScores != null)
	    result.addAll(sGroupWScores.getStudents());
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
}
