package com.banco.model;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String rolUsuario;

    public Usuario(String nombreUsuario, String contrasena, String rolUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rolUsuario = rolUsuario;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
