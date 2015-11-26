package com.inspiracode.inspiraschool.service.cat;

import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.service.BaseService;

public interface StudentService extends BaseService<Student> {
    String getStudentGroups(Student student);
}
