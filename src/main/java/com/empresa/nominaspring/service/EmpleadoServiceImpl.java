package com.empresa.nominaspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.nominaspring.entity.Empleado;
import com.empresa.nominaspring.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public double salario(Empleado e) {
        int sueldoBase = Empleado.SUELDO_BASE[e.getCategoria() - 1];
        return sueldoBase + 5000 * e.getAnyos();
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado listarEmpleado(String dni) {
        return empleadoRepository.findOneByDNI(dni);
    }

    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado editarEmpleado(Empleado empleado) {
        //Usan el mismo metodo (save) pero hago esto para no repetir codigo y mejorar la legibilidad luego (y porque soy un chulo).
        return crearEmpleado(empleado);
    }

    @Override
    public void eliminarEmpleado(String dni) {
        Empleado empleado = listarEmpleado(dni);
        empleadoRepository.delete(empleado);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorDNI(String dni) {
        return empleadoRepository.findByDNI(dni);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorSexo(char sexo) {
        return empleadoRepository.findBySexo(sexo);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorCategoria(int categoria) {
        return empleadoRepository.findByCategoria(categoria);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorAnyos(double anyos) {
        return empleadoRepository.findByAnyos(anyos);
    }


    
}
