package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.service.cat.StudentService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

public class StudentSpringService extends BaseSpringService<Student> implements StudentService {
    private StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
	return studentDAO;
    }

    @Required
    public void setStudentDAO(StudentDAO studentDAO) {
	super.setDaoFactory((BaseDAO<Student>) studentDAO);
	this.studentDAO = studentDAO;
    }
}
