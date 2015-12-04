package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.GroupDAO;
import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.service.cat.GroupService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class GroupSpringService extends BaseSpringService<Group> implements GroupService {
    private static final long serialVersionUID = -981742973428484529L;
    private GroupDAO groupDAO;

    public GroupDAO getGroupDAO() {
	return groupDAO;
    }

    @Required
    public void setGroupDAO(GroupDAO groupDAO) {
	super.setDaoFactory((BaseDAO<Group>) groupDAO);
	this.groupDAO = groupDAO;
    }

    @Override
    public Group getGroupWithAssignments(int groupId) {
	return groupDAO.getGroupWithAssignments(groupId);
    }
}
