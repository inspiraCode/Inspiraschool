package com.inspiracode.inspiraschool.dao.hibernate.cat;

import com.inspiracode.inspiraschool.dao.cat.GroupDAO;
import com.inspiracode.inspiraschool.dao.hibernate.BaseHibernateDAO;
import com.inspiracode.inspiraschool.dto.cat.Group;

public class GroupDAOHibernate extends BaseHibernateDAO<Group> implements GroupDAO {
  public GroupDAOHibernate (){
    super (Group.class);
  }
}
