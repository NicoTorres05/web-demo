<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.tiendaDigital.model.Usuario" %>
<html>
  <head>
    <title>Usuarios</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
  </head>
  <body class="bg-light">
  <%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

  <div class="container p-5 w-50">
    <div class="d-flex justify-content-between">
      <h2>USUARIOS</h2>
      <form action="${pageContext.request.contextPath}/tiendaDigital/usuarios/crear">
        <input class="btn btn-primary" type="submit" value="CREAR" />
      </form>
    </div>
    <table class="table table-striped table-hover text-center align-middle mt-3 mx-auto mb-5">
      <thead class="table-dark">
      <tr>
        <th>ID</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>ROL</th>
        <th>ACCIONES</th>
      </tr>
      </thead>
      <tbody class="table-primary">

      <%
        if (request.getAttribute("usuarios") != null) {
          List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");

          for (Usuario usuario : usuarios) {
      %>

      <tr>
        <td><%= usuario.getIdUsuario()%></td>
        <td><%= usuario.getNombre()%></td>
        <td><%= usuario.getEmail()%></td>
        <td><%= usuario.getPassword().substring(0, 8) + "..."%></td>
        <td><%= usuario.getRol()%></td>
        <td>
          <div class="d-flex justify-content-center">
            <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/usuarios/<%= usuario.getIdUsuario()%>">
              <input class="btn btn-info" type="submit" value="DETALLES" />
            </form>
            <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/usuarios/editar/<%= usuario.getIdUsuario()%>">
              <input class="btn btn-warning" type="submit" value="EDITAR" />
            </form>
            <form action="${pageContext.request.contextPath}/tiendaDigital/usuarios/borrar/" method="post">
              <input type="hidden" name="__method__" value="delete"/>
              <input type="hidden" name="codigo" value="<%= usuario.getIdUsuario()%>"/>
              <input class="btn btn-danger" type="submit" value="ELIMINAR" />
            </form>
          </div>
        </td>
      </tr>

      <%
        }
      } else {
      %>

      <p>No hay registros de usuario</p>

      <% } %>

      </tbody>
    </table>
  </div>
  <%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
  </body>
</html>
