package org.tiendaDigital.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tiendaDigital.dao.CategoriaDAO;
import org.tiendaDigital.dao.CategoriaDAOImpl;
import org.tiendaDigital.model.Categoria;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "categoriaServlet", value = "/tiendaDigital/categorias/*")
public class CategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * 		/categorias/
     * 		/categorias/{id}
     * 		/categorias/editar{id}
     * 		/categorias/crear
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo(); //

        if (pathInfo == null || "/".equals(pathInfo)) {
            CategoriaDAO catDAO = new CategoriaDAOImpl();

            // GET
            //	/categorias/
            //	/categorias

            List<Categoria> cats = catDAO.getAll();

            request.setAttribute("cats", cats);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/categorias.jsp");

        } else {
            // GET
            // 		/categorias/{id}
            // 		/categorias/{id}/
            // 		/categorias/edit/{id}
            // 		/categorias/edit/{id}/
            // 		/categorias/crear
            // 		/categorias/crear/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /categorias/crear
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/categoria-crear.jsp");

            } else if (pathParts.length == 2) {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/detalle-categoria.jsp");

            } else if (pathParts.length == 3 && "editar".equals(pathParts[1]) ) {
                CategoriaDAO catDAO = new CategoriaDAOImpl();

                // GET
                // /categorias/editar/{id}
                try {
                    request.setAttribute("cat",catDAO.find(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/categoria-editar.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/categorias.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoria/categorias.jsp");
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
            CategoriaDAO catDAO = new CategoriaDAOImpl();

            String nombre = request.getParameter("nombre");
            String desc = request.getParameter("desc");
            Categoria nuevaCat = new Categoria();
            nuevaCat.setNombre(nombre);
            nuevaCat.setDescripcion(desc);
            catDAO.create(nuevaCat);

        } else if (__method__ != null && "put".equalsIgnoreCase(__method__)) {
            // Actualizar uno existente
            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if (__method__ != null && "delete".equalsIgnoreCase(__method__)) {
            // Borrar uno existente
            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);
        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tiendaDigital/categorias");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDAO catDAO = new CategoriaDAOImpl();
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String desc = request.getParameter("desc");
        Categoria cat = new Categoria();

        try {
            int id = Integer.parseInt(codigo);
            cat.setIdCategoria(id);
            cat.setNombre(nombre);
            cat.setDescripcion(desc);
            catDAO.update(cat);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        CategoriaDAO catDAO = new CategoriaDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);
            catDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
