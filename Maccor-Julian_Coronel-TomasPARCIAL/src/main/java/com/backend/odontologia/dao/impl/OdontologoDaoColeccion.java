package com.backend.odontologia.dao.impl;

import com.backend.odontologia.dao.IDao;
import com.backend.odontologia.model.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoColeccion implements IDao<Odontologo> {

    private List<Odontologo> odontologos;

    public OdontologoDaoColeccion() {
        this.odontologos = new ArrayList<>();
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        int matricula = odontologos.size() + 1;
        odontologo.setMatricula(matricula);
        odontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public Iterable<Odontologo> listar() {
        return odontologos;
    }
}