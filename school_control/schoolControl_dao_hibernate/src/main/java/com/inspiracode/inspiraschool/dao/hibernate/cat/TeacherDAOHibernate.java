package com.inspiracode.inspiraschool.dao.hibernate.cat;

import java.util.List;

import com.inspiracode.inspiraschool.dao.cat.TeacherDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Teacher;

public class TeacherDAOHibernate extends BaseHibernateDAO<Teacher> implements TeacherDAO {
    public TeacherDAOHibernate() {
	super(Teacher.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Teacher getTeacherByUserName(String userName) {
	List<Teacher> resultList = (List<Teacher>) getHibernateTemplate().find(QUERY_BY_USER_NAME, userName);
	if (resultList.isEmpty()) {
	    return null;
	}
	return resultList.get(0);
    }
}
