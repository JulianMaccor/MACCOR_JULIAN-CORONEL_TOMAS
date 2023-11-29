package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaOdontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;
}
