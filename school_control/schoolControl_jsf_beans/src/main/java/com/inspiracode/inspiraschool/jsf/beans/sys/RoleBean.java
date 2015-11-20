package com.inspiracode.inspiraschool.jsf.beans.sys;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.sys.RoleService;

@ManagedBean
@SessionScoped
public class RoleBean extends BaseFacesBean<Role> {
  private static final long serialVersionUID = -4416613837271284898L;

  private RoleService roleService;

  public RoleBean() {
    super(Role.class);
  }

  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    super.setService((BaseService<Role>) roleService);
    this.roleService = roleService;
  }

}
