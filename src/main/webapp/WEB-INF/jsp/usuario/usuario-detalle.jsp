<%--
  Created by IntelliJ IDEA.
  User: nitor
  Date: 16/01/2025
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="org.tiendaDigital.model.Usuario" %>
<%@page import="java.util.Optional" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Usuario</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column w-25">
    <h2 class="text-center">DETALLES USUARIO</h2>

    <% 	Optional<Usuario> optUsuario = (Optional<Usuario>)request.getAttribute("usuario");
        if (optUsuario.isPresent()) {
    %>

    <div class="mt-5 fs-5">
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">ID</p>
            <p><%= optUsuario.get().getIdUsuario() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Nombre</p>
            <p><%= optUsuario.get().getNombre() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Email</p>
            <p><%= optUsuario.get().getEmail() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Rol</p>
            <p><%= optUsuario.get().getRol() %></p>
        </div>
    </div>

    <a class="btn btn-dark w-25 m-auto" href="<%=application.getContextPath()%>/tiendaDigital/usuarios">VOLVER</a>

    <% 	} else { %>

    request.sendRedirect("usuarios/");

    <% 	} %>

</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
