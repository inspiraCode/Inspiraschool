package com.inspiracode.inspiraschool.dao.cat;

import java.util.Set;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.cross.GroupAssignment;

public interface StudentDAO extends BaseDAO<Student> {
    public final static String QUERY_FETCH_GROUPS = "from Student student left join fetch student.groups WHERE student.id=?";
    Set<GroupAssignment> getStudentGroups(Student student);
}
