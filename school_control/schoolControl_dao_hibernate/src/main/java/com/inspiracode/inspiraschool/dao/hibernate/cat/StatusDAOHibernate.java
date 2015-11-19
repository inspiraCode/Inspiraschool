package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.StatusDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Status;

public class StatusDAOHibernate extends BaseHibernateDAO<Status> implements StatusDAO {
    public StatusDAOHibernate() {
	super(Status.class);
    }
}
