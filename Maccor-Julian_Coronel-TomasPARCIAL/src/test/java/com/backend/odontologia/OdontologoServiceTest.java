package com.backend.odontologia;

import com.backend.odontologia.dao.impl.OdontologoDaoColeccion;
import com.backend.odontologia.dao.impl.OdontologoDaoH2;
import com.backend.odontologia.model.Odontologo;
import com.backend.odontologia.service.OdontologoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaAgregarYListarOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());

        Odontologo odontologoAPersistir = new Odontologo(123, "Juan", "Perez");
        Odontologo odontologoPersistido = odontologoService.guardarOdontologo(odontologoAPersistir);

        assertNotNull(odontologoPersistido);
        Iterable<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertNotNull(odontologos);
    }

    @Test
    void deberiaAgregarYListarOdontologosEnColeccion() {
        odontologoService = new OdontologoService(new OdontologoDaoColeccion());

        Odontologo odontologoAPersistir = new Odontologo(123, "Juan", "Perez");
        Odontologo odontologoPersistido = odontologoService.guardarOdontologo(odontologoAPersistir);

        assertNotNull(odontologoPersistido);
        Iterable<Odontologo> odontologos = odontologoService.listarOdontologos();
        assertNotNull(odontologos);
    }
}