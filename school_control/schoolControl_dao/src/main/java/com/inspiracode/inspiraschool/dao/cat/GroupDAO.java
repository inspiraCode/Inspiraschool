package com.inspiracode.inspiraschool.dao.cat;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.Group;

public interface GroupDAO extends BaseDAO<Group> {
    public static final String QUERY_GROUP_FETCH_ASSIGNMENTS = "from Group g left join fetch g.assignments ga WHERE g.id=?";
    
    Group getGroupWithAssignments(int groupId);
}
