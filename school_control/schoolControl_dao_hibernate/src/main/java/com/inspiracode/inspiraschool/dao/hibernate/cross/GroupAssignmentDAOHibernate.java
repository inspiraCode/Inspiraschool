package com.inspiracode.inspiraschool.dao.hibernate.cross;

import com.inspiracode.inspiraschool.dao.cross.GroupAssignmentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public class GroupAssignmentDAOHibernate extends BaseHibernateDAO<GroupAssignment> implements GroupAssignmentDAO {
  public GroupAssignmentDAOHibernate() {
    super(GroupAssignment.class);
  }
}
