package com.inspiracode.inspiraschool.service.cross;

import java.util.List;

import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;
import com.inspiracode.inspiraschool.service.BaseService;

public interface GroupAssignmentService extends BaseService<GroupAssignment> {
    List<GroupAssignment> getGroupsByTeacher(int teacherId);

    List<Student> getStudentsByGroupId(int groupId);

    List<Student> getActiveStudentsByGroupId(int groupId);
}
