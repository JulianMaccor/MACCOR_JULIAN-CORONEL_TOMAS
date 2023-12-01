package com.backend.clinicaOdontologica.repository;

import com.backend.clinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    public List<Paciente> findAll();

    Paciente findByDni(int dni);
}
