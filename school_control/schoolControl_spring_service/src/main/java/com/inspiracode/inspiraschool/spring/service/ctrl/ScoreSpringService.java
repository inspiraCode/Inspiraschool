package com.inspiracode.inspiraschool.spring.service.ctrl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dto.cat.Student;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class ScoreSpringService extends BaseSpringService<Score> implements ScoreService {
    private static final long serialVersionUID = -6892127825578438605L;
    private static final Logger logger = Logger.getLogger(ScoreSpringService.class.getName());
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
    public List<Score> scoresByGroup(int groupId) {
	return scoreDAO.scoresByGroup(groupId);
    }

    @Override
    public Student studentInScore(int scoreId) {
	Score score = scoreDAO.get(scoreId);
	Student dbStudent = score.getStudent();
	logger.debug("got student from db:" + dbStudent.getName());
	return dbStudent;
    }
}
