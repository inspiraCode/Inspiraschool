package com.inspiracode.inspiraschool.service.ctrl;

import java.util.List;

import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.service.BaseService;

public interface ScoreService extends BaseService<Score> {
    public List<Score> getScoresByGroup(int groupId);
    
    public Score getScoreByStudentAndAssignment(int studentId, int assignmentId);
}
