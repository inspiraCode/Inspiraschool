package com.inspiracode.inspiraschool.jsf.beans.sys;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.sys.User;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.sys.UserService;

@ManagedBean
@SessionScoped
public class UserBean extends BaseFacesBean<User> {
  private static final long serialVersionUID = -2227204283655337632L;

  private UserService userService;

  public UserBean() {
    super(User.class);
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    super.setService((BaseService<User>) userService);
    this.userService = userService;
  }

}
