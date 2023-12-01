package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.turno.PacienteTurnoSalidaDto;
import com.backend.clinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.TurnoRepository;
import com.backend.clinicaOdontologica.service.impl.OdontologoService;
import com.backend.clinicaOdontologica.service.impl.PacienteService;
import com.backend.clinicaOdontologica.service.impl.TurnoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoRepository turnoRepository;


    @Test
    public void deberiaRegistrarUnTurnoParaElPacienteConElOdontologo_ParaElDiaSiguienteAlActual() throws BadRequestException, ResourceNotFoundException {

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Julian", "Maccor", 42855555, LocalDate.of(2023, 10, 18), new DomicilioEntradaDto("Independencia", 501, "Córdoba Capital", "Córdoba"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12345AF", "Tomas", "Coronel");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);


        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto();
        turnoEntradaDto.setPaciente(pacienteSalidaDto.getId());
        turnoEntradaDto.setOdontologo(odontologoSalidaDto.getId());
        turnoEntradaDto.setFechaYHora(LocalDateTime.now().plusDays(1));

        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        System.out.println("ID del paciente: " + turnoSalidaDto.getPacienteTurnoSalidaDto().getId());

        assertNotNull(turnoSalidaDto);


        PacienteTurnoSalidaDto pacienteTurno = turnoSalidaDto.getPacienteTurnoSalidaDto();
        assertNotNull(pacienteTurno);
        assertNotNull(pacienteTurno.getId());


        OdontologoTurnoSalidaDto odontologoTurno = turnoSalidaDto.getOdontologoTurnoSalidaDto();
        assertNotNull(odontologoTurno);
        assertNotNull(odontologoTurno.getId());


        assertNotNull(turnoSalidaDto.getId());
    }

    private void assertIdValido(Long id, String mensaje) {
        assertNotNull(id, mensaje);
        assertTrue(id >= 0, mensaje);
    }
}



