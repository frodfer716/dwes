package com.empresa.nominaspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.nominaspring.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Query(value = "SELECT * FROM empleados WHERE dni=?", nativeQuery = true)
    public Empleado findOneByDNI(String dni);

    @Query(value = "SELECT * FROM empleados WHERE dni=?", nativeQuery = true)
    public List<Empleado> findByDNI(String dni);

    @Query(value = "SELECT * FROM empleados WHERE nombre=?", nativeQuery = true)
    public List<Empleado> findByNombre(String nombre);

    @Query(value = "SELECT * FROM empleados WHERE sexo=?", nativeQuery = true)
    public List<Empleado> findBySexo(char sexo);

    @Query(value = "SELECT * FROM empleados WHERE categoria=?", nativeQuery = true)
    public List<Empleado> findByCategoria(int categoria);

    @Query(value = "SELECT * FROM empleados WHERE anyos=?", nativeQuery = true)
    public List<Empleado> findByAnyos(double anyos);
    
}
