package com.inspiracode.inspiraschool.dao.cross;

import java.util.List;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public interface GroupAssignmentDAO extends BaseDAO<GroupAssignment> {
    public final static String QUERY_BY_TEACHER_ID = "SELECT ga from GroupAssignment ga JOIN ga.teacher t WHERE t.id = ?";
    
    List<GroupAssignment> getGroupsByTeacher(int teacherId);
    List<Student> getStudentsByGroupId(int groupId);
}
