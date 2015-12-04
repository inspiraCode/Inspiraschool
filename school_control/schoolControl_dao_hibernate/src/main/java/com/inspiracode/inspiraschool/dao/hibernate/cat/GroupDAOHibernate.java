package com.inspiracode.inspiraschool.dao.hibernate.cat;

import java.util.List;

import com.inspiracode.inspiraschool.dao.cat.GroupDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Group;

public class GroupDAOHibernate extends BaseHibernateDAO<Group> implements GroupDAO {
    private static final long serialVersionUID = 1L;

    public GroupDAOHibernate() {
	super(Group.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Group getGroupWithAssignments(int groupId) {
	List<Group> dbList = (List<Group>) getHibernateTemplate().find(QUERY_GROUP_FETCH_ASSIGNMENTS, groupId);
	if (dbList.isEmpty()) {
	    return null;
	}

	return dbList.get(0);
    }
}
