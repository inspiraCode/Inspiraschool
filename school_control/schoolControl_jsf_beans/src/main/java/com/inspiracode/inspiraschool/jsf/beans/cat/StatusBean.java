package com.inspiracode.inspiraschool.jsf.beans.cat;

import com.inspiracode.inspiraschool.dto.cat.StudentStatus;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.StatusService;

public class StatusBean extends BaseFacesBean<StudentStatus> {
    private static final long serialVersionUID = -6368682762929686214L;

    private StatusService statusService;

    public StatusBean() {
	super(StudentStatus.class);
    }

    public StatusService getStatusService() {
	return statusService;
    }

    public void setStatusService(StatusService statusService) {
	super.setService((BaseService<StudentStatus>) statusService);
	this.statusService = statusService;
    }
}
