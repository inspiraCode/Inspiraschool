package com.inspiracode.inspiraschool.service.sys;

import java.util.List;

import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.dto.sys.User;
import com.inspiracode.inspiraschool.service.BaseService;

public interface UserService extends BaseService<User> {
    List<Role> rolesInUser(int userId);
}
