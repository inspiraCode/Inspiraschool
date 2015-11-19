package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.TeacherDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Teacher;

public class TeacherDAOHibernate extends BaseHibernateDAO<Teacher> implements TeacherDAO {
  public TeacherDAOHibernate() {
    super(Teacher.class);
  }
}
