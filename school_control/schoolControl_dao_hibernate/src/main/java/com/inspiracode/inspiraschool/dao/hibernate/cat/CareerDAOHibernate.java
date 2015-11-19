package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.CareerDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Career;

public class CareerDAOHibernate extends BaseHibernateDAO<Career> implements CareerDAO {
  public CareerDAOHibernate() {
    super(Career.class);
  }
}
