package com.inspiracode.inspiraschool.jsf.beans.cat;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;

@ManagedBean
@SessionScoped
public class PeriodBean extends BaseFacesBean<Period> {
    private static final long serialVersionUID = -6225946609782103977L;

    @ManagedProperty("#{periodService}")
    private PeriodService periodService;

    public PeriodBean() {
	super(Period.class);
    }

    @Override
    public String addNew() throws InstantiationException, IllegalAccessException {
	Period newPeriod = new Period();
	newPeriod.setPeriodYear(Calendar.getInstance().get(Calendar.YEAR));
	setSelectedItem(newPeriod);

	return "Edit";
    }

    public PeriodService getPeriodService() {
	return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
	super.setService((BaseService<Period>) periodService);
	this.periodService = periodService;
    }

    @Override
    protected boolean validate() {
	boolean result = true;
	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	int selectedYear = getSelectedItem().getPeriodYear();
	if (selectedYear < currentYear || selectedYear > currentYear + 1) {
	    publishError("Revise el año del periodo.");
	    result = false;
	}
	return result;
    }
}
