package org.tiendaDigital.model;

import java.util.Objects;

public class Categoria {
    private int idCategoria;
    private String nombre;
    private String descripcion;

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Categoria categoria)) return false;
        return idCategoria == categoria.idCategoria && nombre.equals(categoria.nombre) && descripcion.equals(categoria.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nombre, descripcion);
    }
}
