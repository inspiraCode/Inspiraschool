package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Group;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.GroupService;

@ManagedBean
@SessionScoped
public class GroupBean extends BaseFacesBean<Group> {
    private static final long serialVersionUID = -1169756132687652990L;

    @ManagedProperty("#{groupService}")
    private GroupService groupService;

    public GroupBean() {
	super(Group.class);
    }

    public GroupService getGroupService() {
	return groupService;
    }

    public void setGroupService(GroupService groupService) {
	super.setService((BaseService<Group>) groupService);
	this.groupService = groupService;
    }

}
