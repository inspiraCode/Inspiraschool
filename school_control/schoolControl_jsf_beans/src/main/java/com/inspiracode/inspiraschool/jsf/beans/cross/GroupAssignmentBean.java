package com.inspiracode.inspiraschool.jsf.beans.cross;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.inspiracode.inspiraschool.dto.cat.Teacher;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.TeacherService;
import com.inspiracode.inspiraschool.service.cross.GroupAssignmentService;

@ManagedBean
@SessionScoped
public class GroupAssignmentBean extends BaseFacesBean<GroupAssignment> {
    private static final long serialVersionUID = 1901316763469701174L;

    @ManagedProperty("#{groupAssignmentService}")
    private GroupAssignmentService groupAssignmentService;

    @ManagedProperty("#{teacherService}")
    private TeacherService teacherService;

    public GroupAssignmentBean() {
	super(GroupAssignment.class);
    }

    public List<GroupAssignment> getGroupsByLoggedTeacher() {
	String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
	Teacher loggedTeacher = teacherService.getTeacherByUserName(userName);
	return groupAssignmentService.getGroupsByTeacher(loggedTeacher.getId());
    }

    public GroupAssignmentService getGroupAssignmentService() {
	return groupAssignmentService;
    }

    public void setGroupAssignmentService(GroupAssignmentService groupAssignmentService) {
	super.setService((BaseService<GroupAssignment>) groupAssignmentService);
	this.groupAssignmentService = groupAssignmentService;
    }

    public TeacherService getTeacherService() {
	return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
	this.teacherService = teacherService;
    }
}
