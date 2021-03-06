package com.inspiracode.inspiraschool.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public interface BaseDAO<T extends BaseDTO> extends Serializable {
    /**
     * Retrieve an instance of Dominable object by it's ID.
     * @param id
     * @return
     */
    T get(int id);

    /**
     * Retrieve all dominable records from database.
     * @return
     */
    List<T> getAll();

    /**
     * Save and return its ID
     * @param object
     * @return
     */
    int add(T object);

    /**
     * Execute update.
     * @param object
     */
    void update(T object);

    /**
     * Remove a dominable from database.
     * @param object
     */
    void delete(T object);
    
    /**
     * 
     * @return a SQL Connection
     * @throws SQLException 
     */
    Connection getConnection() throws SQLException;
}
