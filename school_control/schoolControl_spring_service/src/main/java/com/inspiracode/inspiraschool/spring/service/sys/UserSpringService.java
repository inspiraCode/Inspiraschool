package com.inspiracode.inspiraschool.spring.service.sys;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.sys.UserDAO;
import com.inspiracode.inspiraschool.dto.sys.User;
import com.inspiracode.inspiraschool.service.sys.UserService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class UserSpringService extends BaseSpringService<User> implements UserService {
    private static final long serialVersionUID = 4223862244912886057L;
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
	return userDAO;
    }

    @Required
    public void setUserDAO(UserDAO userDAO) {
	super.setDaoFactory((BaseDAO<User>) userDAO);
	this.userDAO = userDAO;
    }

}
