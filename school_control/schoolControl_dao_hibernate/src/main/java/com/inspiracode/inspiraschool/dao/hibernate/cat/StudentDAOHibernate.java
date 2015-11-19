package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;

public class StudentDAOHibernate extends BaseHibernateDAO<Student> implements StudentDAO {
    public StudentDAOHibernate(){
	super(Student.class);
    }
}
