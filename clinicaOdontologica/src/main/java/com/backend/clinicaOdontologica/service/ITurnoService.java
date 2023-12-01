package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaOdontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto cambiarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;
}
