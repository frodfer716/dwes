package com.empresa.nominaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/editarEmpleado/{dni}")
    public String editarEmpleado(@PathVariable String dni, Model modelo) {
        modelo.addAttribute("empleado", empleadoService.listarEmpleado(dni));
        return "editarEmpleado";
    }

    @PostMapping("/listarEmpleados/{dni}")
    public String editar(@PathVariable String dni, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        Empleado empleadoExistente = empleadoService.listarEmpleado(dni);

        empleadoExistente.setDni(dni);
        empleadoExistente.setNombre(empleado.getNombre());
        empleadoExistente.setSexo(empleado.getSexo());
        empleadoExistente.setCategoria(empleado.getCategoria());
        empleadoExistente.setAnyos(empleado.getAnyos());

        empleadoService.editarEmpleado(empleadoExistente);
        return "redirect:/listarEmpleados";
    }

    @GetMapping("/eliminarEmpleado/{dni}")
    public String eliminarEmpleado(@PathVariable String dni) {
        empleadoService.eliminarEmpleado(dni);
        return "redirect:/listarEmpleados";
    }

    @GetMapping("/listarSalarios")
    public String listarSalarios(Model modelo) {
        modelo.addAttribute("empleado", new Empleado());
        return "listarSalarios";
    }

    @GetMapping("/buscarSalarios")
    public String buscarSalarios(@RequestParam String dni, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorDNI(dni));
        modelo.addAttribute("salario", empleadoService.salario(empleadoService.listarEmpleado(dni)));
        return "listarSalarios";
    }

    @GetMapping("/buscarEmpleados")
    public String buscarEmpleados(Model modelo) {
        modelo.addAttribute("empleado", new Empleado());
        return "buscarEmpleados";
    }

    @GetMapping("/buscarEmpleadosPorDNI")
    public String buscarEmpleadosPorDNI(@RequestParam String dni, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorDNI(dni));
        return "buscarEmpleados";
    }

    @GetMapping("/buscarEmpleadosPorNombre")
    public String buscarEmpleadosPorNombre(@RequestParam String nombre, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorNombre(nombre));
        return "buscarEmpleados";
    }

    @GetMapping("/buscarEmpleadosPorSexo")
    public String buscarEmpleadosPorSexo(@RequestParam char sexo, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorSexo(sexo));
        return "buscarEmpleados";
    }

    @GetMapping("/buscarEmpleadosPorCategoria")
    public String buscarEmpleadosPorCategoria(@RequestParam int categoria, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorCategoria(categoria));
        return "buscarEmpleados";
    }

    @GetMapping("/buscarEmpleadosPorAnyos")
    public String buscarEmpleadosPorAnyos(@RequestParam double anyos, @ModelAttribute("empleado") Empleado empleado, Model modelo) {
        modelo.addAttribute("empleados", empleadoService.buscarEmpleadosPorAnyos(anyos));
        return "buscarEmpleados";
    }
    
}
