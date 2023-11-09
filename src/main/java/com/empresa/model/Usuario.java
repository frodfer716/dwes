package com.empresa.model;

import java.util.UUID;

/**
 * Clase Usuario
 */
public class Usuario {
    private String id;
    private String name;
    private String surnames;
    private String email;
    private String password;

    /**
     * Constructor Usuario completo
     * @param name
     * @param surnames
     * @param email
     * @param password
     */
    public Usuario(String name, String surnames, String email, String password) {
        // Generar un nuevo ID único utilizando UUID
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
    }

    /**
     * Getter de ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Getter de Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter de Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de Surnames
     * @return
     */
    public String getSurnames() {
        return surnames;
    }

    /**
     * Setter de Surnames
     * @param surnames
     */
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    /**
     * Getter de Email
     * @return
     */
    public String getEmail() {
        return email;
    }
 

    /**
     * Getter de Password
     * @return
     */
    public String getPassword() {
        return password;
    }

}