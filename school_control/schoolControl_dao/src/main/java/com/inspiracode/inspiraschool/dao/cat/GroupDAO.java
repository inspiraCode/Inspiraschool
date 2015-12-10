package com.inspiracode.inspiraschool.dao.cat;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.Group;

public interface GroupDAO extends BaseDAO<Group> {
    public static final String QUERY_GROUP_FETCH_ASSIGNMENTS = "from Group g left join fetch g.assignments ga WHERE g.id=?";
    public static final String QUERY_GROUP_FETCH_STUDENTS = "from Group g left join fetch g.assignments ga left join fetch ga.students s WHERE g.id=?";

    Group getGroupWithAssignments(int groupId);

    Group getGroupWithStudents(int groupId);
}
