package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.CompanyDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Company;

public class CompanyDAOHibernate extends BaseHibernateDAO<Company> implements CompanyDAO {
    private static final long serialVersionUID = 1L;

    public CompanyDAOHibernate() {
	super(Company.class);
    }
}
