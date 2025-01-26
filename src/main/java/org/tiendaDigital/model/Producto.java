package org.tiendaDigital.model;

import java.util.Objects;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int idCategoria;

    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto producto)) return false;
        return idProducto == producto.idProducto && nombre.equals(producto.nombre) && descripcion.equals(producto.descripcion)  && idCategoria == producto.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, nombre, descripcion, precio, idCategoria);
    }
}
