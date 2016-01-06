package com.inspiracode.inspiraschool.jsf.beans.sys;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.dto.sys.User;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.sys.RoleService;
import com.inspiracode.inspiraschool.service.sys.UserService;

@ManagedBean
@SessionScoped
public class UserBean extends BaseFacesBean<User> {
    private static final long serialVersionUID = -2227204283655337632L;

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{roleService}")
    private RoleService roleService;

    private String confirmPassword;

    private List<Role> availableRoles = new ArrayList<Role>();

    public UserBean() {
	super(User.class);
    }

    public List<Role> getAvailableRoles() {
	if (availableRoles.isEmpty()) {
	    availableRoles.addAll(roleService.getAll());
	}

	availableRoles.removeAll(getSelectedItem().getRoles());
	return availableRoles;
    }

    public List<Role> getAssignedRoles() {
	List<Role> result = new ArrayList<Role>();
	result.addAll(getSelectedItem().getRoles());
	return result;
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

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }

    public RoleService getRoleService() {
	return roleService;
    }

    public void setRoleService(RoleService roleService) {
	this.roleService = roleService;
    }

}
