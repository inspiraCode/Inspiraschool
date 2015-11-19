package com.inspiracode.inspiraschool.jsf.beans.cat;

import com.inspiracode.inspiraschool.dto.cat.Status;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.StatusService;

public class StatusBean extends BaseFacesBean<Status> {
    private static final long serialVersionUID = -6368682762929686214L;

    private StatusService statusService;

    public StatusBean() {
	super(Status.class);
    }

    public StatusService getStatusService() {
	return statusService;
    }

    public void setStatusService(StatusService statusService) {
	super.setService((BaseService<Status>) statusService);
	this.statusService = statusService;
    }
}
