package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaOdontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;


public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);

    PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto paciente);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto buscarPacientePorDni(int dni);
}
