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
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado editarEmpleado(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarEmpleado'");
    }

    @Override
    public void eliminarEmpleado(String dni) {
        Empleado empleado = empleadoRepository.findByDNI(dni);
        empleadoRepository.delete(empleado);
    }

    @Override
    public Empleado buscarSalario(String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarSalario'");
    }

    @Override
    public Empleado buscarEmpleadoPorDNI(String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEmpleadoPorDNI'");
    }

    @Override
    public Empleado buscarEmpleadoPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEmpleadoPorNombre'");
    }

    @Override
    public Empleado buscarEmpleadoPorSexo(String sexo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEmpleadoPorSexo'");
    }

    @Override
    public Empleado buscarEmpleadoPorCategoria(int categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEmpleadoPorCategoria'");
    }

    @Override
    public Empleado buscarEmpleadoPorAnyos(double anyos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEmpleadoPorAnyos'");
    }


    
}
