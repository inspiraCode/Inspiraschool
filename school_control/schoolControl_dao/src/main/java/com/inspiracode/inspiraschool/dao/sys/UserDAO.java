package com.inspiracode.inspiraschool.dao.sys;

import java.util.List;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.dto.sys.User;

public interface UserDAO extends BaseDAO<User> {
    public final static String QUERY_BY_USER_NAME = "from User user WHERE user.userName = ?";
    public final static String QUERY_USER_WITH_ROLES = "from User user left join fetch user.roles WHERE user.id = ?";
    User getByName(String name);
    List<Role> getRolesByUser(int userId);
}
