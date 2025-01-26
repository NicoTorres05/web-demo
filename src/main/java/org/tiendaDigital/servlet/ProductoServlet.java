package org.tiendaDigital.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tiendaDigital.dao.CategoriaDAOImpl;
import org.tiendaDigital.dao.ProductoDAO;
import org.tiendaDigital.dao.ProductoDAOImpl;
import org.tiendaDigital.model.Producto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "productoServlet", value = "/tiendaDigital/productos/*")
public class ProductoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/productos/
     * 		/productos/{id}
     * 		/productos/editar{id}
     * 		/productos/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            ProductoDAO prodDAO = new ProductoDAOImpl();

            // GET
            //	/productos/
            //	/productos

            String filter = request.getParameter("filter");
            List<Producto> prods = null;

                prods = prodDAO.getAll();


            request.setAttribute("prods", prods);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/productos.jsp");

        } else {
            // GET
            // 		/productos/{id}
            // 		/productos/{id}/
            // 		/productos/edit/{id}
            // 		/productos/edit/{id}/
            // 		/productos/crear
            // 		/productos/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                CategoriaDAOImpl catDAO = new CategoriaDAOImpl();

                // GET
                // /productos/crear

                request.setAttribute("cats", catDAO.getAll());
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/producto-crear.jsp");

            } else if (pathParts.length == 2) {
                ProductoDAO prodDAO = new ProductoDAOImpl();
                // GET
                // /productos/{id}
                try {
                    request.setAttribute("prod",prodDAO.find(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/producto-detalle.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/productos.jsp");
                }
            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
                ProductoDAO prodDAO = new ProductoDAOImpl();
                CategoriaDAOImpl catDAO = new CategoriaDAOImpl();

                // GET
                // /productos/editar/{id}

                try {
                    request.setAttribute("prod",prodDAO.find(Integer.parseInt(pathParts[2])));
                    request.setAttribute("cats", catDAO.getAll());
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/producto-editar.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/productos.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/producto/productos.jsp");
            }
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            // Crear uno nuevo
            ProductoDAO prodDAO = new ProductoDAOImpl();

            String nombre = request.getParameter("nombre");
            String desc = request.getParameter("desc");
            String precio = request.getParameter("precio");
            String idCat = request.getParameter("idCat");

            Producto nuevoProd = new Producto();

            nuevoProd.setNombre(nombre);
            nuevoProd.setDescripcion(desc);
            nuevoProd.setPrecio(Double.parseDouble(precio));
            nuevoProd.setIdCategoria(Integer.parseInt(idCat));

            prodDAO.create(nuevoProd);
        }  else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);

        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tiendaDigital/productos");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO prodDAO = new ProductoDAOImpl();

        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String desc = request.getParameter("desc");
        String precio = request.getParameter("precio");
        String idCat = request.getParameter("idCat");
        Producto prod = new Producto();

        try {

            int id = Integer.parseInt(codigo);

            prod.setIdProducto(id);
            prod.setNombre(nombre);
            prod.setDescripcion(desc);
            prod.setPrecio(Double.parseDouble(precio));
            prod.setIdCategoria(Integer.parseInt(idCat));

            prodDAO.update(prod);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        ProductoDAO prodDAO = new ProductoDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);
            prodDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

}
