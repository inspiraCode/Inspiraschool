package com.inspiracode.inspiraschool.dao.hibernate.ctrl;

import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public class ScoreDAOHibernate extends BaseHibernateDAO<Score> implements ScoreDAO {
  public ScoreDAOHibernate() {
    super(Score.class);
  }
}
