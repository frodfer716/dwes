package com.empresa.nominaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.empresa.nominaspring.entity.Empleado;
import com.empresa.nominaspring.repository.EmpleadoRepository;

@SpringBootApplication
public class NominaSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NominaSpringApplication.class, args);
	}

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		Empleado e1 = new Empleado("33000033A", "Fernando Alonso", 'M', 3, 3);
		empleadoRepository.save(e1);
		
		Empleado e2 = new Empleado("33000034A", "Antonio Lobato", 'M', 4, 5);
		empleadoRepository.save(e2);
		*/
	}

}
