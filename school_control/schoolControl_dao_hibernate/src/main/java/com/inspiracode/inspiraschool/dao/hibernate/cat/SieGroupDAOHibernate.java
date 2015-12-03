package com.inspiracode.inspiraschool.dao.hibernate.cat;

import java.util.List;

import com.inspiracode.inspiraschool.dao.cat.SieGroupDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;

public class SieGroupDAOHibernate extends BaseHibernateDAO<SieGroup> implements SieGroupDAO {
    private static final long serialVersionUID = 4780196851900586690L;

    public SieGroupDAOHibernate() {
	super(SieGroup.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public SieGroup getSieWithScores(int sieGroupId) {
	List<SieGroup> resultList = (List<SieGroup>) getHibernateTemplate().find(QUERY_FETCH_SCORES, sieGroupId);
	if (resultList.isEmpty()) {
	    return null;
	}
	return resultList.get(0);
    }

}
