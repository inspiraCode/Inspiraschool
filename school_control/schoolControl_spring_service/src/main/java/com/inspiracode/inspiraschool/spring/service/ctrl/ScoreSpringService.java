package com.inspiracode.inspiraschool.spring.service.ctrl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class ScoreSpringService extends BaseSpringService<Score> implements ScoreService {
  private ScoreDAO scoreDAO;

  public ScoreDAO getScoreDAO() {
    return scoreDAO;
  }

  @Required
  public void setScoreDAO(ScoreDAO scoreDAO) {
    super.setDaoFactory((BaseDAO<Score>) scoreDAO);
    this.scoreDAO = scoreDAO;
  }
}
