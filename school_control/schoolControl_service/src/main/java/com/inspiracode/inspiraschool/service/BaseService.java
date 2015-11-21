package com.inspiracode.inspiraschool.service;

import java.io.Serializable;
import java.util.List;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public interface BaseService<T extends BaseDTO> extends Serializable {
    T get(int id);

    List<T> getAll();

    int add(T object);

    void update(T object);

    void delete(T object);
    
    void deleteAll(List<T> items);
}
