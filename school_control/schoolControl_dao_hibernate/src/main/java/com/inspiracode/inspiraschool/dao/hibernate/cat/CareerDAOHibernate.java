package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.CareerDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Career;

public class CareerDAOHibernate extends BaseHibernateDAO<Career> implements CareerDAO {
    private static final long serialVersionUID = 1L;

    public CareerDAOHibernate() {
	super(Career.class);
    }
}
