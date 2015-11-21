package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.AssignmentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Assignment;

public class AssignmentDAOHibernate extends BaseHibernateDAO<Assignment> implements AssignmentDAO {
    private static final long serialVersionUID = 1L;

    public AssignmentDAOHibernate() {
	super(Assignment.class);
    }

}
