package com.inspiracode.inspiraschool.dao.sys;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.sys.User;

public interface UserDAO extends BaseDAO<User> {
    public final static String QUERY_BY_USER_NAME = "from User user WHERE user.userName = ?";
    User getByName(String name);
}
