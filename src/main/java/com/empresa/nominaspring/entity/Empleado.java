package com.empresa.nominaspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "sexo", nullable = false)
    private char sexo;

    @Column(name = "categoria", nullable = false)
    private int categoria;

    @Column(name = "anyos", nullable = false)
    private double anyos;
    
}
