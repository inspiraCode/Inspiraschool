package com.inspiracode.inspiraschool.spring.service.cross;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cross.GroupAssignmentDAO;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.service.cross.GroupAssignmentService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class GroupAssignmentSpringService extends BaseSpringService<GroupAssignment> implements GroupAssignmentService {
    private GroupAssignmentDAO groupAssignmentDAO;

    @Override
    public List<GroupAssignment> getGroupsByTeacher(int teacherId) {
	return groupAssignmentDAO.getGroupsByTeacher(teacherId);
    }

    public GroupAssignmentDAO getGroupAssignmentDAO() {
	return groupAssignmentDAO;
    }

    @Required
    public void setGroupAssignmentDAO(GroupAssignmentDAO groupAssignmentDAO) {
	super.setDaoFactory((BaseDAO<GroupAssignment>) groupAssignmentDAO);
	this.groupAssignmentDAO = groupAssignmentDAO;
    }
}