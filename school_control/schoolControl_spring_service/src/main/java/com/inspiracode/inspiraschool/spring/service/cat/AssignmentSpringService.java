package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.AssignmentDAO;
import com.inspiracode.inspiraschool.dto.cat.Assignment;
import com.inspiracode.inspiraschool.service.cat.AssignmentService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class AssignmentSpringService extends BaseSpringService<Assignment> implements AssignmentService {
    private static final long serialVersionUID = -7687943828742680210L;
    private AssignmentDAO assignmentDAO;

    public AssignmentDAO getAssignmentDAO() {
	return assignmentDAO;
    }

    @Required
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
	super.setDaoFactory((BaseDAO<Assignment>) assignmentDAO);
	this.assignmentDAO = assignmentDAO;
    }

}
