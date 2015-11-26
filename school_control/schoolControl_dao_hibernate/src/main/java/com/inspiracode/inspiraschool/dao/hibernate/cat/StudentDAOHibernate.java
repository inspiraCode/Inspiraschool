package com.inspiracode.inspiraschool.dao.hibernate.cat;

import java.util.List;
import java.util.Set;

import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public class StudentDAOHibernate extends BaseHibernateDAO<Student> implements StudentDAO {
    private static final long serialVersionUID = -2495491427442697009L;

    public StudentDAOHibernate() {
	super(Student.class);
    }

    @Override
    public Set<GroupAssignment> getStudentGroups(Student student) {
	@SuppressWarnings("unchecked")
	List<Student> resultList = (List<Student>) getHibernateTemplate().find(QUERY_FETCH_GROUPS, student.getId());
	if (resultList.isEmpty()) {
	    return null;
	}
	Student dbStudent = resultList.get(0);
	return dbStudent.getGroups();
    }
}
