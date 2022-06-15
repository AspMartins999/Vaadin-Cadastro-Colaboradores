package com.example.application.data.repository;

import com.example.application.data.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

}
