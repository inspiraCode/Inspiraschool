package com.inspiracode.inspiraschool.spring.service.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class ScoreSpringService extends BaseSpringService<Score> implements ScoreService {
    private static final long serialVersionUID = -6892127825578438605L;
    private ScoreDAO scoreDAO;

    public ScoreDAO getScoreDAO() {
	return scoreDAO;
    }

    @Required
    public void setScoreDAO(ScoreDAO scoreDAO) {
	super.setDaoFactory((BaseDAO<Score>) scoreDAO);
	this.scoreDAO = scoreDAO;
    }

    @Override
    public List<Score> getScoresByGroup(int groupId) {
	return scoreDAO.scoresByGroup(groupId);
    }

    @Override
    public Score getScoreByStudentAndAssignment(int studentId, int assignmentId) {
	return scoreDAO.scoreByStudentAndAssignment(studentId, assignmentId);
    }
}
