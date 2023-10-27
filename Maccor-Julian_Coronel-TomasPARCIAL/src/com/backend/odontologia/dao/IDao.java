package com.backend.odontologia.dao;

public interface IDao<T> {
    T registrar(T t);
    Iterable<T> listar();
}
