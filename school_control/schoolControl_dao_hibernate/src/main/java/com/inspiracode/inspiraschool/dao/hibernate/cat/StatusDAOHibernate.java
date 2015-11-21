package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.StatusDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.StudentStatus;

public class StatusDAOHibernate extends BaseHibernateDAO<StudentStatus> implements StatusDAO {
    private static final long serialVersionUID = 1L;

    public StatusDAOHibernate() {
	super(StudentStatus.class);
    }
}
