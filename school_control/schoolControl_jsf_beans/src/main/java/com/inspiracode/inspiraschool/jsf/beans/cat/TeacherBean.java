package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Teacher;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.TeacherService;

@ManagedBean
@SessionScoped
public class TeacherBean extends BaseFacesBean<Teacher> {
    private static final long serialVersionUID = 8009608422922058973L;

    @ManagedProperty("#{teacherService}")
    private TeacherService teacherService;

    public TeacherBean() {
	super(Teacher.class);
    }

    public TeacherService getTeacherService() {
	return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
	super.setService((BaseService<Teacher>) teacherService);
	this.teacherService = teacherService;
    }

}
