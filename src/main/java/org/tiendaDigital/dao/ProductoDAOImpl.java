package org.tiendaDigital.dao;

import org.tiendaDigital.model.Producto;

import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO {
    @Override
    public void create(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO producto (nombre, precio, descripcion, idCategoria) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setString(idx++, producto.getDescripcion());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setInt(idx,producto.getIdCategoria());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("INSERT de producto con 0 filas insertadas.");
            }

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                producto.setIdProducto(rsGenKeys.getInt(1));
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
    public List<Producto> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Producto> prods = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM producto");
            while (rs.next()) {
                Producto prod = new Producto();
                int idx = 1;

                prod.setIdProducto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setIdCategoria(rs.getInt(idx));

                prods.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return prods;
    }

    @Override
    public Optional<Producto> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM producto WHERE idProducto = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto prod = new Producto();
                idx = 1;

                prod.setIdProducto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setIdCategoria(rs.getInt(idx));

                return Optional.of(prod);
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
    public void update(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, idCategoria = ? WHERE idProducto = ?");
            int idx = 1;

            ps.setString(idx++, producto.getNombre());
            ps.setString(idx++, producto.getDescripcion());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setInt(idx++, producto.getIdCategoria());
            ps.setInt(idx, producto.getIdProducto());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Update de producto con 0 registros actualizados.");
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

            ps = conn.prepareStatement("DELETE FROM producto WHERE idProducto = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Delete de producto con 0 registros eliminados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }
}
