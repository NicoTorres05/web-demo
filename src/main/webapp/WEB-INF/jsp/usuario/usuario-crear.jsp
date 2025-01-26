<%--
  Created by IntelliJ IDEA.
  User: nitor
  Date: 16/01/2025
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrarse</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>

<div class="container p-5 d-flex flex-column">
    <h2 class="text-center">REGISTRARSE</h2>
    <form class="form my-5 mx-auto w-25" action="${pageContext.request.contextPath}/tiendaDigital/usuarios/crear/" method="post">
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Nombre</label>
            <input class="form-control ms-3" type="text" name="nombre" required>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Email</label>
            <input class="form-control ms-3" type="text" name="email" required>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Password</label>
            <input class="form-control ms-3" type="password" name="password" required>
        </div>
        <div class="d-flex justify-content-between mb-5">
            <label class="my-auto fs-5 fw-semibold">Rol</label>
            <select class="form-control ms-3 w-50" name="rol">
                <option value="Cliente">Cliente</option>
                <option value="Admin">Admin</option>
            </select>
        </div>
        <div class="m-auto text-center">
            <button class="btn btn-dark">
                <a class="text-light link-underline link-underline-opacity-0" href="<%=application.getContextPath()%>/tiendaDigital/usuarios">VOLVER</a>
            </button>
            <input class="btn btn-primary ms-3" type="submit" value="REGISTRAR">
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>

