package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.PeriodDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Period;

public class PeriodDAOHibernate extends BaseHibernateDAO<Period> implements PeriodDAO {
    private static final long serialVersionUID = 457289898633715536L;

    public PeriodDAOHibernate() {
	super(Period.class);
    }
}
