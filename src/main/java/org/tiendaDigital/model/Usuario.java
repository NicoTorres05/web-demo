package org.tiendaDigital.model;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario usuario)) return false;
        return idUsuario == usuario.idUsuario && nombre.equals(usuario.nombre) && email.equals(usuario.email) && password.equals(usuario.password) && rol == usuario.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, email, password, rol);
    }
}
