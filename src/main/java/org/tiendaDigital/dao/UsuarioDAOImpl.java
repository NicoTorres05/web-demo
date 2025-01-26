package org.tiendaDigital.dao;

import org.tiendaDigital.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO {
    @Override
    public void create(Usuario usuario) {
        System.out.println("Entrando en el método create...");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }

            ps = conn.prepareStatement("INSERT INTO usuario (nombre, email, password, rol) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            // Imprimir los valores que se van a insertar
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Password: " + usuario.getPassword());
            System.out.println("Rol: " + usuario.getRol());

            int idx = 1;
            ps.setString(idx++, usuario.getNombre());
            ps.setString(idx++, usuario.getEmail());
            ps.setString(idx++, usuario.getPassword());
            ps.setString(idx, usuario.getRol());

            int rows = ps.executeUpdate();
            if (rows == 0) { System.out.println("INSERT de usuario con 0 filas insertadas."); }

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                usuario.setIdUsuario(rsGenKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Usuario> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Usuario> users = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM usuario");
            while (rs.next()) {
                Usuario user = new Usuario();
                int idx = 1;

                user.setIdUsuario(rs.getInt(idx++));
                user.setNombre(rs.getString(idx++));
                user.setEmail(rs.getString(idx++));
                user.setPassword(rs.getString(idx++));
                user.setRol(rs.getString(idx));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return users;
    }

    @Override
    public Optional<Usuario> getById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM usuario WHERE idUsuario = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario user = new Usuario();
                idx = 1;

                user.setIdUsuario(rs.getInt(idx++));
                user.setNombre(rs.getString(idx++));
                user.setEmail(rs.getString(idx++));
                user.setPassword(rs.getString(idx++));
                user.setRol(rs.getString(idx));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE usuario SET nombre = ?, email = ?, password = ?, rol = ?  WHERE idUsuario = ?");

            int idx = 1;

            ps.setString(idx++, usuario.getNombre());
            ps.setString(idx++, usuario.getEmail());
            ps.setString(idx++, usuario.getPassword());
            ps.setString(idx++, usuario.getRol());
            ps.setInt(idx, usuario.getIdUsuario());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Update de usuario con 0 registros actualizados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0) { System.out.println("Delete de usuario con 0 registros eliminados."); }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }
}
