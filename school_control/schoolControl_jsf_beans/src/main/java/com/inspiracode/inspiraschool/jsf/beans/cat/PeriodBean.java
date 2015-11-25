package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.PeriodService;

@ManagedBean
@SessionScoped
public class PeriodBean extends BaseFacesBean<Period> {
    private static final long serialVersionUID = -6225946609782103977L;

    private PeriodService periodService;

    public PeriodBean() {
	super(Period.class);
    }

    public PeriodService getPeriodService() {
	return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
	super.setService((BaseService<Period>) periodService);
	this.periodService = periodService;
    }
}
