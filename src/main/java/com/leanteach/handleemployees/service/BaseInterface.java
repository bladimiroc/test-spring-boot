package com.leanteach.handleemployees.service;

import java.util.List;

public interface BaseInterface<T> {
    public List<T> getAll();
    public T getById(int id);
    public T add(T t);
    public T update(T t);
    public boolean delete(int id);
}
