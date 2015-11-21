package com.inspiracode.inspiraschool.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.BaseDTO;
import com.inspiracode.inspiraschool.service.BaseService;

public abstract class BaseSpringService<T extends BaseDTO> implements BaseService<T> {
    private static final long serialVersionUID = 1L;
    private BaseDAO<T> daoFactory;
    
    public BaseSpringService(){}
    
    public T get(int id) {
	return (T) daoFactory.get(id);
    }

    public List<T> getAll() {
	List<T> result = daoFactory.getAll();
	return result != null ? (List<T>) result : null;
    }

    @Transactional(readOnly = false)
    public int add(T object) {
	return daoFactory.add(object);
    }

    @Transactional(readOnly = false)
    public void update(T object) {
	daoFactory.update(object);
    }

    @Transactional(readOnly = false)
    public void delete(T object) {
	daoFactory.delete(object);
    }

    @Transactional(readOnly = false)
    public void deleteAll(List<T> items) {
	for (T item : items) {
	    delete(item);
	}
    }

    public BaseDAO<T> getDaoFactory() {
	return daoFactory;
    }

    public void setDaoFactory(BaseDAO<T> daoFactory) {
	this.daoFactory = daoFactory;
    }
}
