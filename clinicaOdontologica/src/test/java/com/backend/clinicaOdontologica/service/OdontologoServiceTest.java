package com.backend.clinicaOdontologica.service;


import com.backend.clinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    public void deberiaCrearUnOdontologo() throws ResourceNotFoundException {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();

    }

}
