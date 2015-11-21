package com.inspiracode.inspiraschool.dao.ctrl;

import java.util.List;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public interface ScoreDAO extends BaseDAO<Score> {
    public static final String QUERY_SCORES_BY_GROUP = "from Score s JOIN s.groupAssignment ga WHERE ga.id = ?";
    
    List<Score> scoresByGroup(int groupId);
}
