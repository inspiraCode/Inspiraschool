package com.inspiracode.inspiraschool.dao.ctrl;

import java.util.List;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public interface ScoreDAO extends BaseDAO<Score> {
    public static final String QUERY_SCORE_BY_STUDENT = "from Score sc left join fetch sc.groupAssignment ga WHERE sc.student.id=?";
    
    List<Score> scoresByGroup(int groupId);

    Score scoreByStudentAndAssignment(int studentId, int assignmentId);
}
