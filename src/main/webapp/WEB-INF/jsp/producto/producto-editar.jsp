<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.Optional" %>
<%@ page import="org.tiendaDigital.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="org.tiendaDigital.model.Producto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column">
    <h2 class="text-center">EDITAR PRODUCTO</h2>

    <% 	Optional<Producto> optProd = (Optional<Producto>)request.getAttribute("prod");
        if (optProd.isPresent()) {
    %>

    <form class="form my-5 mx-auto w-50" action="${pageContext.request.contextPath}/tiendaDigital/productos/crear/" method="post">
        <input type="hidden" name="__method__" value="put" />
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">ID</label>
            <input class="form-control ms-3 w-25" type="text" name="codigo" value="<%= optProd.get().getIdProducto() %>" readonly>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Nombre</label>
            <input class="form-control ms-3 w-50" type="text" name="nombre" value="<%= optProd.get().getNombre() %>" required>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Descripcion</label>
            <input class="form-control ms-3" type="text" name="desc" value="<%= optProd.get().getDescripcion() %>">
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Precio</label>
            <input class="form-control ms-3 w-25" type="number" step="0.01" min="0" name="precio" value="<%= optProd.get().getPrecio() %>" placeholder="€" required>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Categoria</label>
            <select class="form-control ms-3 w-50" name="idCat">

                <%
                    if (request.getAttribute("cats") != null) {
                        List<Categoria> cats = (List<Categoria>) request.getAttribute("cats");
                        for (Categoria cat : cats) {

                %>

                <option value="<%= cat.getIdCategoria() %>"><%= cat.getNombre() %></option>

                <% } } %>

            </select>
        </div>

        <div class="m-auto text-center">
            <button class="btn btn-dark">
                <a class="text-light link-underline link-underline-opacity-0" href="<%=application.getContextPath()%>/tiendaDigital/productos">VOLVER</a>
            </button>
            <input class="btn btn-primary ms-3" type="submit" value="EDITAR">
        </div>
    </form>
</div>

<% 	} else { %>

request.sendRedirect("productos/");

<% 	} %>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
