package com.backend.clinicaOdontologica.service;


import com.backend.clinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.impl.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void deberiaAgregarUnOdontologoDeNombreTomasYRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("AAFG2020", "Tomas", "Coronel");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Tomas", odontologoSalidaDto.getNombre());
    }


    @org.junit.jupiter.api.Test
    @Order(2)
    public void deberiaActualizarAlOdontologoDeId1_TomasCoronel_A_AgustinMolina() throws ResourceNotFoundException {

        OdontologoModificacionEntradaDto odontologoEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoEntradaDto.setId(1L);
        odontologoEntradaDto.setNombre("Agustin");
        odontologoEntradaDto.setApellido("Molina");

        OdontologoSalidaDto odontologoActualizado = odontologoService.actualizarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoActualizado.getId());
        assertEquals("Molina", odontologoActualizado.getApellido());
    }


    @Test
    @Order(3)
    public void deberiaListarTodosLosOdontologosRegistrados() {

        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologos();

        assertNotNull(odontologos);
        assertTrue(odontologos.size() > 0);
    }


}



