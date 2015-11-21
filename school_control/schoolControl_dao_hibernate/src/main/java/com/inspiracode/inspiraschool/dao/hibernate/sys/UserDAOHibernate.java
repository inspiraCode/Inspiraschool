package com.inspiracode.inspiraschool.dao.hibernate.sys;

import java.util.List;

import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dao.sys.UserDAO;
import com.inspiracode.inspiraschool.dto.sys.User;

public class UserDAOHibernate extends BaseHibernateDAO<User> implements UserDAO {
    private static final long serialVersionUID = 1L;

    public UserDAOHibernate() {
	super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getByName(String name) {
	List<User> users = (List<User>) getHibernateTemplate().find(QUERY_BY_USER_NAME, name);
	if (users.isEmpty())
	    return null;

	return users.get(0);
    }
}
