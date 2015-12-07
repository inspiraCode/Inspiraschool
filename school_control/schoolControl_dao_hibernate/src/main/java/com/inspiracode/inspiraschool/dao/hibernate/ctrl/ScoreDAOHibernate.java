package com.inspiracode.inspiraschool.dao.hibernate.ctrl;

import java.util.ArrayList;
import java.util.List;

import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public class ScoreDAOHibernate extends BaseHibernateDAO<Score> implements ScoreDAO {
    private static final long serialVersionUID = 1L;

    public ScoreDAOHibernate() {
	super(Score.class);
    }

    @Override
    public List<Score> scoresByGroup(int groupId) {
	List<Score> temp = getAll();
	List<Score> result = new ArrayList<Score>();
	for (Score score : temp) {
	    if (score.getGroupAssignment().getId() == groupId && score.getStudent().getStudentStatus().getId() == 1) {
		result.add(score);
	    }
	}
	return result;
    }
}
