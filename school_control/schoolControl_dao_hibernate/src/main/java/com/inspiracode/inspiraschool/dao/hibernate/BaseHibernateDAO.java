package com.inspiracode.inspiraschool.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.BaseDTO;

public abstract class BaseHibernateDAO<T extends BaseDTO> extends HibernateDaoSupport implements BaseDAO<T> {
    private static final long serialVersionUID = 1L;
    private Class<T> type;

    public BaseHibernateDAO(Class<T> type) {
	super();
	this.type = type;
    }

    public T get(int id) {
	return getHibernateTemplate().get(type, id);
    }

    public List<T> getAll() {
	return getHibernateTemplate().loadAll(type);
    }

    public int add(T object) {
	getHibernateTemplate().save(object);
	getHibernateTemplate().flush();
	return object.getId();
    }

    public void update(T object) {
	getHibernateTemplate().update(object);
	getHibernateTemplate().flush();
    }

    public void delete(T object) {
	getHibernateTemplate().delete(object);
	getHibernateTemplate().flush();
    }

    public void indexEntity(T object) {
	FullTextSession fullTextSession = Search.getFullTextSession(this.getSessionFactory().getCurrentSession());
	ScrollableResults results = fullTextSession.createCriteria(this.type).scroll(ScrollMode.FORWARD_ONLY);
	int counter = 0, numItemsInGroup = 10;
	while (results.next()) {
	    fullTextSession.index(results.get(0));
	    if (counter++ % numItemsInGroup == 0) {
		fullTextSession.flushToIndexes();
		fullTextSession.clear();
	    }
	}
    }

    public Connection getConnection() throws SQLException {
	SessionFactoryImplementor sfi = (SessionFactoryImplementor) this.getSessionFactory();
	ConnectionProvider cp = sfi.getConnectionProvider();
	return cp.getConnection();
    }

    protected Session conn() {
	return getSessionFactory().getCurrentSession();
    }

    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
	this.setSessionFactory(sessionFactory);
    }
}
