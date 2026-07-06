package sn.uasz.m1.tp2.dao;

import java.util.List;

public interface IDao<T> {
    boolean create(T o);
    boolean update(T o);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();
}