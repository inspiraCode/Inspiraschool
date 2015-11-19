package com.inspiracode.inspiraschool.spring.service;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.BaseDTO;


public abstract class BaseHibernateIndexableService<T extends BaseDTO> extends BaseSpringService<T> {
    private BaseHibernateDAO<T> daoFactory;

    public void indexEntity(T object) {
	daoFactory.indexEntity(object);
    }
    
    @Override
    public BaseHibernateDAO<T> getDaoFactory() {
	return daoFactory;
    }

    public void setDaoFactory(BaseHibernateDAO<T> daoFactory) {
	super.setDaoFactory((BaseDAO<T>) daoFactory);
	this.daoFactory = daoFactory;
    }
}
