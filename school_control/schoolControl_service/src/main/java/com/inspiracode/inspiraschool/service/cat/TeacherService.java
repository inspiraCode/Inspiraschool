package com.inspiracode.inspiraschool.service.cat;

import com.inspiracode.inspiraschool.dto.cat.Teacher;
import com.inspiracode.inspiraschool.service.BaseService;

public interface TeacherService extends BaseService<Teacher> {
    Teacher getTeacherByUserName(String userName);
}
