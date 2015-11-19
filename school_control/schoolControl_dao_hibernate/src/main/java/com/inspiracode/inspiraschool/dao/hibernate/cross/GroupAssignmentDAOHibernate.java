package com.inspiracode.inspiraschool.dao.hibernate.cross;

import com.inspiracode.inspiraschool.dao.cross.GroupassignmentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public class GroupAssignmentDAOHibernate extends BaseHibernateDAO<GroupAssignment> implements GroupassignmentDAO {
  public GroupAssignmentDAOHibernate() {
    super(GroupAssignment.class);
  }
}
