<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="org.tiendaDigital.model.Categoria" %>
<%@page import="java.util.Optional" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Categoria</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column">
    <h2 class="text-center">EDITAR CATEGORIA</h2>

    <% 	Optional<Categoria> optCat = (Optional<Categoria>)request.getAttribute("cat");
        if (optCat.isPresent()) {
    %>

    <form class="form my-5 mx-auto w-25" action="${pageContext.request.contextPath}/tiendaDigital/categorias/editar/" method="post">
        <input type="hidden" name="__method__" value="put" />
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">ID</label>
            <input class="form-control ms-3 w-25" type="text" name="codigo" value="<%= optCat.get().getIdCategoria() %>" readonly>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Nombre</label>
            <input class="form-control ms-3" type="text" name="nombre" value="<%= optCat.get().getNombre() %>">
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Nombre</label>
            <input class="form-control ms-3" type="text" name="desc" value="<%= optCat.get().getDescripcion() %>">
        </div>
        <div class="m-auto text-center">
            <button class="btn btn-dark">
                <a class="text-light link-underline link-underline-opacity-0" href="<%=application.getContextPath()%>/tiendaDigital/categorias">VOLVER</a>
            </button>
            <input class="btn btn-primary ms-3" type="submit" value="EDITAR">
        </div>
    </form>
</div>

<% 	} else { %>

request.sendRedirect("categorias/");

<% 	} %>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
