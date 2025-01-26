package org.tiendaDigital.dao;

import org.tiendaDigital.model.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioDAO {
    public void create(Usuario usuario);
    public List<Usuario> getAll();
    public Optional<Usuario> getById(int id);
    public void update(Usuario usuario);
    public void delete(int id);

}
