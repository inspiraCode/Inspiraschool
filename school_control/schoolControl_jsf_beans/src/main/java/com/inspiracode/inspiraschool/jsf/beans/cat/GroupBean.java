package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Career;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.CareerService;
import com.inspiracode.inspiraschool.service.cat.GroupService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;

@ManagedBean
@SessionScoped
public class GroupBean extends BaseFacesBean<Group> {
    private static final long serialVersionUID = -1169756132687652990L;

    @ManagedProperty("#{groupService}")
    private GroupService groupService;

    @ManagedProperty("#{periodService}")
    private PeriodService periodService;
    @ManagedProperty("#{careerService}")
    private CareerService careerService;

    public GroupBean() {
	super(Group.class);
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

}
