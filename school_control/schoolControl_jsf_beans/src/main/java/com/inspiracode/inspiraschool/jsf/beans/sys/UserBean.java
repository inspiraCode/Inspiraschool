package com.inspiracode.inspiraschool.jsf.beans.sys;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.dto.sys.User;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.sys.UserService;

@ManagedBean
@SessionScoped
public class UserBean extends BaseFacesBean<User> {
    private static final long serialVersionUID = -2227204283655337632L;

    @ManagedProperty("#{userService}")
    private UserService userService;

    public UserBean() {
	super(User.class);
    }

    public String getRolesByUser(User user) {
	String result = "";
	List<Role> roles = userService.rolesInUser(user.getId());
	if (!roles.isEmpty()) {
	    for (Role role : roles) {
		if (!result.contains(role.getName() + ", ")) {
		    result += role.getName() + ", ";
		}
	    }
	}

	if (result.length() > 2) {
	    result = result.substring(0, result.length() - 2);
	}

	return result;
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	super.setService((BaseService<User>) userService);
	this.userService = userService;
    }

    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }

}
