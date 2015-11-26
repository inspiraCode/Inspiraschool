package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.SieGroupDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;

public class SieGroupDAOHibernate extends BaseHibernateDAO<SieGroup> implements SieGroupDAO {
    private static final long serialVersionUID = 4780196851900586690L;

    public SieGroupDAOHibernate() {
	super(SieGroup.class);
    }
}
