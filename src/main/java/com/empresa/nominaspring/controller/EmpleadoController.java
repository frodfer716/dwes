package com.empresa.nominaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpleadoController {

    @GetMapping("/empresa")
    public String index() {
        System.out.println("hola");
        return "index";
    }
    
}
