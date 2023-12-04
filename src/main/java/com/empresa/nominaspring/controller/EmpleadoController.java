package com.empresa.nominaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.nominaspring.entity.Empleado;
import com.empresa.nominaspring.service.EmpleadoService;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listarEmpleados")
    public String listarEmpleados(Model modelo) {
        modelo.addAttribute("empleados", empleadoService.listarEmpleados());
        return "listarEmpleados";
    }

    @GetMapping("/crearEmpleado")
    public String crearEmpleado(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("empleado", empleado);
        return "crearEmpleado";
    }

    @PostMapping("/listarEmpleados")
    public String guardar(@ModelAttribute("empleado") Empleado empleado) {
        empleadoService.crearEmpleado(empleado);
        return "redirect:/listarEmpleados";
    }
    
}
