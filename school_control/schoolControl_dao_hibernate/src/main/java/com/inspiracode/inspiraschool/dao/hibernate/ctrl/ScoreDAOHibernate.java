package com.inspiracode.inspiraschool.dao.hibernate.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dao.ctrl.ScoreDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.ctrl.Score;

public class ScoreDAOHibernate extends BaseHibernateDAO<Score> implements ScoreDAO {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ScoreDAOHibernate.class.getName());

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

    @SuppressWarnings("unchecked")
    @Override
    public Score scoreByStudentAndAssignment(int studentId, int assignmentId) {
	List<Score> dbList = (List<Score>) getHibernateTemplate().find(QUERY_SCORE_BY_STUDENT, studentId);
	if (dbList.isEmpty())
	    return null;

	for (Score score : dbList) {
	    logger.debug("comparing: " + score.getGroupAssignment().getAssignment().getId() + " to " + assignmentId);
	    if (score.getGroupAssignment().getAssignment().getId() == assignmentId) {
		return score;
	    }
	}

	return null;
    }
}
