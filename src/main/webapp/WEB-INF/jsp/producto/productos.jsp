<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.tiendaDigital.model.Producto"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>



<div class="container p-5">
    <form action="${pageContext.request.contextPath}/tiendaDigital/productos/">
        <div class="d-flex mb-3 w-50">
            <input class="form-control w-25 me-3" type="text" name="filter" placeholder="Por nombre...">
            <input class="btn btn-info" type="submit" value="Filtrar">
        </div>
    </form>



        <div class="d-flex justify-content-between">
            <h2>PRODUCTOS</h2>



            <form action="${pageContext.request.contextPath}/sgames/productos/crear">
                <input class="btn btn-primary" type="submit" value="CREAR" />
            </form>



        </div>
        <table class="table table-striped table-hover text-center align-middle mt-3 mx-auto mb-5">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>
                <th>PRECIO</th>
                <th>CATEGORIA</th>
                <th>COMPRAR</th>
                <th>ACCIONES</th>



            </tr>
            </thead>
            <tbody class="table-primary">

            <%
                if (request.getAttribute("prods") != null) {
                    List<Producto> prods = (List<Producto>)request.getAttribute("prods");

                    for (Producto prod : prods) {
            %>

            <tr>
                <td><%= prod.getIdProducto()%></td>
                <td class="text-start"><%= prod.getNombre()%></td>
                <td class="text-start"><%= prod.getDescripcion()%></td>
                <td><%= prod.getPrecio()%> €</td>
                <td><%= prod.getIdCategoria()%></td>



                <td>
                    <div class="d-flex justify-content-center">
                        <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/productos/<%= prod.getIdProducto()%>">
                            <input class="btn btn-info" type="submit" value="DETALLES" />
                        </form>
                        <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/productos/editar/<%= prod.getIdProducto()%>">
                            <input class="btn btn-warning" type="submit" value="EDITAR" />
                        </form>
                        <form action="${pageContext.request.contextPath}/tiendaDigital/productos/borrar/" method="post">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= prod.getIdProducto()%>"/>
                            <input class="btn btn-danger" type="submit" value="ELIMINAR" />
                        </form>
                    </div>
                </td>



            </tr>

            <%
                }
            } else {
            %>

            <p>No hay registros de producto</p>

            <% } %>

            </tbody>
        </table>
    </div>
    <%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
