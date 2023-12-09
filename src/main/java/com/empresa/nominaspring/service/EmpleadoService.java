package com.empresa.nominaspring.service;

import java.util.List;

import com.empresa.nominaspring.entity.Empleado;

public interface EmpleadoService {
    
    public double salario(Empleado e);

    public List<Empleado> listarEmpleados();

    public Empleado listarEmpleado(String dni);

    public Empleado crearEmpleado(Empleado empleado);

    public Empleado editarEmpleado(Empleado empleado);

    public void eliminarEmpleado(String dni);

    public List<Empleado> buscarEmpleadosPorDNI(String dni);

    public List<Empleado> buscarEmpleadosPorNombre(String nombre);

    public List<Empleado> buscarEmpleadosPorSexo(char sexo);

    public List<Empleado> buscarEmpleadosPorCategoria(int categoria);

    public List<Empleado> buscarEmpleadosPorAnyos(double anyos);
    
}
