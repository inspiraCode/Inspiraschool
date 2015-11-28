package com.inspiracode.inspiraschool.service.cat;

import java.util.Set;

import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.service.BaseService;

public interface StudentService extends BaseService<Student> {
    String getStudentGroups(Student student);
    
    Set<GroupAssignment> getStudentGroupsList(Student student);
}
