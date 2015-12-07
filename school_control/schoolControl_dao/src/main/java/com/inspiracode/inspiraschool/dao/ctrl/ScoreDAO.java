package com.inspiracode.inspiraschool.dao.ctrl;

import java.util.List;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public interface ScoreDAO extends BaseDAO<Score> {
    List<Score> scoresByGroup(int groupId);
}
