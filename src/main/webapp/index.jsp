<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Demo</title>
  <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<div class="container d-flex py-5 mb-5 text-center">
  <div class="m-auto w-75">
    <p class="fs-4 mb-4">Esta es una aplicación demo full-stack diseñada para gestionar clientes, productos y categorías con operaciones CRUD básicas. 
        El proyecto fue construido con Jakarta EE, utilizando Servlets para manejar las solicitudes HTTP.</p>
  </div>
</div>
<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>