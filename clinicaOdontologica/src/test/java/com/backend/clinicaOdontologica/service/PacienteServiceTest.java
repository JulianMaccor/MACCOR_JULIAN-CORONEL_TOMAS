package com.backend.clinicaOdontologica.service;


import com.backend.clinicaOdontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.impl.PacienteService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void deberiaAgregarUnPacienteDeNombreJulianYRetornarSuId() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Julian", "Maccor", 42855555, LocalDate.of(2023, 10, 18), new DomicilioEntradaDto("Independencia", 501, "Córdoba Capital", "Córdoba"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Julian", pacienteSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    public void deberiaIntentarEliminarElPacienteConId1_SiNoLoEncontraraLanzariaUnaResourseNotFoundException() {

        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(3)
    public void deberiaRegistrar_Y_MostrarEnListaAElPacienteRegistrado() {

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Rodriguez", 12345678, LocalDate.of(1990, 1, 1), new DomicilioEntradaDto("Bv. San Juan", 123, "Cordoba", "Cordoba"));
        pacienteService.registrarPaciente(pacienteEntradaDto);

        List<PacienteSalidaDto> pacientes = pacienteService.listarPacientes();

        assertNotNull(pacientes);
        assertTrue(pacientes.size() > 0);
    }


}
