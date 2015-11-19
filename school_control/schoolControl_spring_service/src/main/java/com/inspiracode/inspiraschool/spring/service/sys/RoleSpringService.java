package com.inspiracode.inspiraschool.spring.service.sys;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.sys.RoleDAO;
import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.service.sys.RoleService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class RoleSpringService extends BaseSpringService<Role> implements RoleService {
  private RoleDAO roleDAO;

  public RoleDAO getRoleDAO() {
    return roleDAO;
  }

  @Required
  public void setRoleDAO(RoleDAO roleDAO) {
    super.setDaoFactory((BaseDAO<Role>) roleDAO);
    this.roleDAO = roleDAO;
  }
}
