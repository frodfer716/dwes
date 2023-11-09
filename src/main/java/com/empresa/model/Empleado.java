package com.empresa.model;

/**
 * Clase Empleado que extiende de Persona
 */
public class Empleado extends Persona {
	
	/**
	 * Declaracion de variables
	 * Variable categoria solo admite numeros entre 1 y 10
	 * Variable anyos solo admite numeros mayores o iguales a 0
	 */
	Persona p;
	private int categoria;
	private double anyos;
	
	
	/**
	 * Constructor Empleado completo
	 * @param nombre
	 * @param dni
	 * @param sexo
	 * @param categoria
	 * @param anyos
	 */
	public Empleado(String dni, String nombre, char sexo, int categoria, double anyos) {
		super(dni, nombre, sexo);
		this.categoria = categoria;
		this.anyos = anyos;
	}
	
	

	/**
	 * Constructor Empleado sin entrada de categoria
	 * Por defecto categoria = 1 y anyos = 0
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Empleado(String dni, String nombre, char sexo) {
		super(dni, nombre, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}
	
	/**
	 * Constructor Empleado vacio
	 */
	public Empleado() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter de categoria
	 * @return
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Getter de anyos
	 * @return
	 */
	public double getAnyos() {
		return anyos;
	}
	
	/**
	 * Setter de categoria
	 * @param categoria
	 */
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Setter de anyos
	 * @param anyos
	 */
	public void setAnyos(double anyos) {
		this.anyos = anyos;
	}

}
