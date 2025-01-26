package org.tiendaDigital.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tiendaDigital.dao.UsuarioDAO;
import org.tiendaDigital.dao.UsuarioDAOImpl;
import org.tiendaDigital.model.Usuario;
import org.tiendaDigital.util.Util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@WebServlet(name = "usuarioServlet", value = "/tiendaDigital/usuarios/*")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * HTTP Method: GET
     * Paths:
     * /usuarios/
     * /usuarios/{id}
     * /usuarios/editar{id}
     * /usuarios/crear
     * /usuarios/login
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
             UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

            //GET
            //	/usuarios/
            //	/usuarios

            request.setAttribute("usuarios", usuarioDAO.getAll());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuarios.jsp");

        } else {
            // GET
            // 		/usuarios/{id}
            // 		/usuarios/{id}/
            // 		/usuarios/edit/{id}
            // 		/usuarios/edit/{id}/
            // 		/usuarios/crear
            // 		/usuarios/crear/
            // 		/usuarios/login/

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {

                // GET
                // /usuarios/crear

                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuario-crear.jsp");

            }  else if (pathParts.length == 2) {
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

                // GET
                // /usuarios/{id}

                try {
                    request.setAttribute("usuario", usuarioDAO.getById(Integer.parseInt(pathParts[1])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuario-detalle.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuarios.jsp");
                }

            } else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

                // GET
                // /usuarios/editar/{id}

                try {
                    request.setAttribute("usuario", usuarioDAO.getById(Integer.parseInt(pathParts[2])));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuario-editar.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuarios.jsp");
                }
            } else {
                System.out.println("Opción POST no soportada.");
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuario/usuarios.jsp");
            }
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String __method__ = request.getParameter("__method__");

        String pathInfo = request.getPathInfo();
        pathInfo = pathInfo.replaceAll("/$", "");
        String[] pathParts = pathInfo.split("/");

        if (__method__ == null) {
            // POST
            // /usuarios/crear/
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            System.out.println("Nombre recibido: " + nombre);
            System.out.println("Email recibido: " + email);
            System.out.println("Password recibido: " + password);
            System.out.println("Rol recibido: " + rol);

            Usuario nuevoUsuario = new Usuario();

            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(email);
            try {
                nuevoUsuario.setPassword(Util.hashPassword(password));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            nuevoUsuario.setRol(rol);

            usuarioDAO.create(nuevoUsuario);

        } else if ("put".equalsIgnoreCase(__method__)) {
            // POST
            // /usuarios/editar/{id}

            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización PUT.
            doPut(request, response);

        } else if ("delete".equalsIgnoreCase(__method__)) {
            // Eliminar
            // Dado que los forms de html sólo soportan method GET y POST utilizo parámetro oculto para indicar la operación de actulización DELETE.
            doDelete(request, response);

        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tiendaDigital/usuarios");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario();

        try {

            int id = Integer.parseInt(codigo);
            usuario.setIdUsuario(id);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(Util.hashPassword(password));
            usuario.setRol(rol);

            usuarioDAO.update(usuario);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher;
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String codigo = request.getParameter("codigo");

        try {
            int id = Integer.parseInt(codigo);
            usuarioDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }


}
