package com.inspiracode.inspiraschool.dao.cat;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.Teacher;

public interface TeacherDAO extends BaseDAO<Teacher> {
    public final static String QUERY_BY_USER_NAME = "from Teacher teacher where teacher.userName = ?";

    Teacher getTeacherByUserName(String userName);

}
