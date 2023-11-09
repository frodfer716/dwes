package com.empresa.model;

/**
 * Clase Persona
 */
public class Persona {
	
	/**
	 * Declaracion de variables
	 */
	public String dni;
	public String nombre;
	public char sexo;
	
	
	/**
	 * Constructor Persona completo
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Persona(String dni, String nombre, char sexo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
	}

	/**
	 * Constructor Persona sin dni
	 * @param nombre
	 * @param sexo
	 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}
	
	/**
	 * Constructor Persona vacio
	 */
	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter de dni
	 * @return
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Getter de nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Getter de categoria
	 * @return
	 */
	public char getSexo() {
		return sexo;
	}
	
	/**
	 * Setter de dni
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Setter de nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Setter de sexo
	 * @param sexo
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

}
