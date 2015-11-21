package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.StatusDAO;
import com.inspiracode.inspiraschool.dto.cat.StudentStatus;
import com.inspiracode.inspiraschool.service.cat.StatusService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

public class StatusSpringService extends BaseSpringService<StudentStatus> implements StatusService {
    private static final long serialVersionUID = -7386514818291126789L;
    private StatusDAO statusDAO;

    public StatusDAO getStatusDAO() {
	return statusDAO;
    }

    @Required
    public void setStatusDAO(StatusDAO statusDAO) {
	super.setDaoFactory((BaseDAO<StudentStatus>) statusDAO);
	this.statusDAO = statusDAO;
    }
}
