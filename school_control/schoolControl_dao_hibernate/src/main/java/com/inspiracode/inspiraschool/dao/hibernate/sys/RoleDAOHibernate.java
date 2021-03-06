package com.inspiracode.inspiraschool.dao.hibernate.sys;

import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dao.sys.RoleDAO;
import com.inspiracode.inspiraschool.dto.sys.Role;

public class RoleDAOHibernate extends BaseHibernateDAO<Role> implements RoleDAO {
    private static final long serialVersionUID = 1L;

    public RoleDAOHibernate() {
	super(Role.class);
    }
}
