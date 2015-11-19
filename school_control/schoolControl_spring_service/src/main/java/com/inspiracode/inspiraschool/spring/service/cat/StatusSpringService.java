package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.StatusDAO;
import com.inspiracode.inspiraschool.dto.cat.Status;
import com.inspiracode.inspiraschool.service.cat.StatusService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

public class StatusSpringService extends BaseSpringService<Status> implements StatusService {
    private StatusDAO statusDAO;

    public StatusDAO getStatusDAO() {
	return statusDAO;
    }

    @Required
    public void setStatusDAO(StatusDAO statusDAO) {
	super.setDaoFactory((BaseDAO<Status>) statusDAO);
	this.statusDAO = statusDAO;
    }
}
