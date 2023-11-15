package com.backend.odontologia.service;

import com.backend.odontologia.dao.IDao;
import com.backend.odontologia.model.Odontologo;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {

        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {

        return odontologoIDao.registrar(odontologo);
    }

    public Iterable<Odontologo> listarOdontologos() {

        return odontologoIDao.listar();
    }
}