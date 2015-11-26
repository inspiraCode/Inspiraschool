package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.StudentService;

@ManagedBean
@SessionScoped
public class StudentBean extends BaseFacesBean<Student> {
    private static final long serialVersionUID = 8490610255861915518L;
    
    @ManagedProperty("#{studentService}")
    private StudentService studentService;

    public StudentBean() {
	super(Student.class);
    }
    
    @Override
    protected boolean validate() {
	// TODO Auto-generated method stub
	return true;
    }
    
    public String getStudentGroups(Student item){
	return studentService.getStudentGroups(item);
    }

    public StudentService getStudentService() {
	return studentService;
    }

    public void setStudentService(StudentService studentService) {
	super.setService((BaseService<Student>) studentService);
	this.studentService = studentService;
    }
}
