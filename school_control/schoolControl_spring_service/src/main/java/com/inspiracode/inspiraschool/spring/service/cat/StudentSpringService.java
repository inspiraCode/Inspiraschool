package com.inspiracode.inspiraschool.spring.service.cat;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.service.cat.StudentService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

public class StudentSpringService extends BaseSpringService<Student> implements StudentService {
    private static final long serialVersionUID = -3461391344550006086L;
    private StudentDAO studentDAO;
    
    @Override
    public String getStudentGroups(Student student) {
	String result = "";
	String groupGrade = "";
	Set<GroupAssignment> sGroups = studentDAO.getStudentGroups(student);
	for (GroupAssignment ga : sGroups) {
	    groupGrade = ga.getGroup().getGrade();
	    if (!result.contains(groupGrade + ", ")) {
		result += ga.getGroup().getGrade() + ", ";
	    }
	}

	if (result.length() > 2) {
	    result = result.substring(0, result.length() - 2);
	}
	
	return result;
    }

    public StudentDAO getStudentDAO() {
	return studentDAO;
    }

    @Required
    public void setStudentDAO(StudentDAO studentDAO) {
	super.setDaoFactory((BaseDAO<Student>) studentDAO);
	this.studentDAO = studentDAO;
    }

}
