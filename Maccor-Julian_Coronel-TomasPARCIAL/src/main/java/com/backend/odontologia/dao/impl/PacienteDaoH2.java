package com.backend.odontologia.dao.impl;

import com.backend.odontologia.dao.IDao;
import com.backend.odontologia.model.Paciente;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class PacienteDaoH2 implements IDao<Paciente> {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
    private DomicilioDaoH2 domicilioDaoH2;
    @Override
    public Paciente registrar(Paciente paciente) {
        return null;
    }

    @Override
    public Iterable<Paciente> listar() {
        return null;
    }

    @Override
    public Paciente buscarPorId(int id) {
        return null;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return null;
    }
}
