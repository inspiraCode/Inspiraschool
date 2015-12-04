package com.inspiracode.inspiraschool.spring.service.cat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;
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
	String groupDayTrip = "";
	String groupName = "";
	Set<GroupAssignment> sGroups = studentDAO.getStudentGroups(student);
	if (sGroups == null)
	    return "";
	for (GroupAssignment ga : sGroups) {
	    groupGrade = ga.getGroup().getGrade();
	    groupDayTrip = ga.getGroup().getDayTrip();
	    groupName = groupGrade + " " + groupDayTrip;
	    if (!result.contains(groupName + ", ")) {
		result += groupName + ", ";
	    }
	}

	if (result.length() > 2) {
	    result = result.substring(0, result.length() - 2);
	}

	return result;
    }

    @Override
    public Set<GroupAssignment> getStudentGroupsList(Student student) {
	return studentDAO.getStudentGroups(student);
    }
    
    @Override
    public Set<SieGroup> getStudentSieGroups(Student student) {
	return studentDAO.getStudentSieGroups(student);
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
