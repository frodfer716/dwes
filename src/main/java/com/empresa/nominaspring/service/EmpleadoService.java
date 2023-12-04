package com.empresa.nominaspring.service;

import java.util.List;

import com.empresa.nominaspring.entity.Empleado;

public interface EmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado crearEmpleado(Empleado empleado);

    public Empleado editarEmpleado(Empleado empleado);

    public void eliminarEmpleado(String dni);

    public Empleado buscarSalario(String dni);

    public Empleado buscarEmpleadoPorDNI(String dni);

    public Empleado buscarEmpleadoPorNombre(String nombre);

    public Empleado buscarEmpleadoPorSexo(String sexo);

    public Empleado buscarEmpleadoPorCategoria(int categoria);

    public Empleado buscarEmpleadoPorAnyos(double anyos);
    
}
