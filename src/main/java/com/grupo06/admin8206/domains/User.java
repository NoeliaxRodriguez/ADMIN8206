package com.grupo06.admin8206.domains;
import java.io.Serializable;


/**
 * The persistent class for the users database table.
 *
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private int admin;

    private String apellido1;

    private String apellido2;

    private String correo;

    private String nombre;

    private String password;

    private int telefono;

    public User() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdmin() {
        return this.admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
