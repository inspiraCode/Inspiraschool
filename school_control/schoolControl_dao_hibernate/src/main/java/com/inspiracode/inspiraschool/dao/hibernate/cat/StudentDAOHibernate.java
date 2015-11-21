package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.StudentDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;

public class StudentDAOHibernate extends BaseHibernateDAO<Student> implements StudentDAO {
    private static final long serialVersionUID = -2495491427442697009L;

    public StudentDAOHibernate(){
	super(Student.class);
    }
}
