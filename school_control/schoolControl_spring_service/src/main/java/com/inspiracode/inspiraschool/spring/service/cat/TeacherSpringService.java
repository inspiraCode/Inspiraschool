package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.TeacherDAO;
import com.inspiracode.inspiraschool.dto.cat.Teacher;
import com.inspiracode.inspiraschool.service.cat.TeacherService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class TeacherSpringService extends BaseSpringService<Teacher> implements TeacherService {
    private static final long serialVersionUID = -758994607857949208L;
    private TeacherDAO teacherDAO;

    public TeacherDAO getTeacherDAO() {
	return teacherDAO;
    }

    @Required
    public void setTeacherDAO(TeacherDAO teacherDAO) {
	super.setDaoFactory((BaseDAO<Teacher>) teacherDAO);
	this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher getTeacherByUserName(String userName) {
	return teacherDAO.getTeacherByUserName(userName);
    }
}
