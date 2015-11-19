package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.AssignmentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Assignment;

public class AssignmentDAOHibernate extends BaseHibernateDAO<Assignment> implements AssignmentDAO {
  public AssignmentDAOHibernate() {
    super(Assignment.class);
  }

}
