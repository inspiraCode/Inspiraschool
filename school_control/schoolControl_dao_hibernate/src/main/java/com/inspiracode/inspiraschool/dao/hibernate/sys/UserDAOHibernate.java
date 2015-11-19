package com.inspiracode.inspiraschool.dao.hibernate.sys;

import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dao.sys.UserDAO;
import com.inspiracode.inspiraschool.dto.sys.User;

public class UserDAOHibernate extends BaseHibernateDAO<User> implements UserDAO {
  public UserDAOHibernate() {
    super(User.class);
  }
}
