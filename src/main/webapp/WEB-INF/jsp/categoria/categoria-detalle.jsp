<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.Optional" %>
<%@ page import="org.tiendaDigital.model.Categoria" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Categoria</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column w-25">
    <h2 class="text-center">DETALLES CATEGORIA</h2>

    <% 	Optional<Categoria> optCat = (Optional<Categoria>)request.getAttribute("cat");
        if (optCat.isPresent()) {
    %>

    <div class="mt-5 fs-5">
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">ID</p>
            <p><%= optCat.get().getIdCategoria() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Nombre</p>
            <p><%= optCat.get().getNombre() %></p>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <p class="fw-semibold">Descripcion</p>
            <p><%= optCat.get().getDescripcion() %></p>
        </div>
    </div>

    <a class="btn btn-dark w-25 m-auto" href="<%=application.getContextPath()%>/tiendaDigital/categorias">VOLVER</a>

    <% 	} else { %>

    request.sendRedirect("categorias/");

    <% 	} %>

</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
