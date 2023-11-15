package com.backend.odontologia.dao;

public interface IDao<T> {
    T registrar(T t);

    Iterable<T> listar();

    T buscarPorId(int id);

    T actualizar(T t);
}
