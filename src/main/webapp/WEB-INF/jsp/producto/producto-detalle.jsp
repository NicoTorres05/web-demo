<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="org.tiendaDigital.model.Producto" %>
<%@page import="java.util.Optional" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Producto</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column w-50">
    <h2 class="text-center">DETALLES PRODUCTO</h2>

    <% 	Optional<Producto> optProd = (Optional<Producto>)request.getAttribute("prod");
        if (optProd.isPresent()) {
    %>

    <div class="mt-5 fs-5">
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">ID</p>
            <p><%= optProd.get().getIdProducto() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Nombre</p>
            <p><%= optProd.get().getNombre() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Descripcion</p>
            <p><%= optProd.get().getDescripcion() %></p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Precio</p>
            <p><%= optProd.get().getPrecio() %> €</p>
        </div>
        <div class="d-flex justify-content-between mb-3">
            <p class="fw-semibold">Categoria</p>
            <p><%= optProd.get().getIdCategoria() %></p>
        </div>
    </div>

    <a class="btn btn-dark w-25 m-auto" href="<%=application.getContextPath()%>/tiendaDigital/productos">VOLVER</a>

    <% 	} else { %>

    request.sendRedirect("productos/");

    <% 	} %>

</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
