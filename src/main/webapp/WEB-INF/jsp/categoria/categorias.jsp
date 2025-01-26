<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="org.tiendaDigital.model.Categoria"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categorias</title>
    <%@ include file="/WEB-INF/jsp/fragmentos/bootstrap.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>



<div class="container p-5 w-50">



    <div class="container p-5 w-25">



        <div class="d-flex justify-content-between">
            <h2>CATEGORIAS</h2>



            <form action="${pageContext.request.contextPath}/sgames/categorias/crear">
                <input class="btn btn-primary" type="submit" value="CREAR" />
            </form>



        </div>
        <table class="table table-striped table-hover text-center align-middle mt-3 mx-auto mb-5">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>



                <th>ACCIONES</th>


            </tr>
            </thead>
            <tbody class="table-primary">

            <%
                if (request.getAttribute("catsDTO") != null) {
                    List<Categoria> catsDTO = (List<Categoria>)request.getAttribute("catsDTO");

                    for (Categoria cat : catsDTO) {
            %>

            <tr>
                <td><%= cat.getIdCategoria()%></td>
                <td class="text-start"><%= cat.getNombre()%></td>
                <td><%= cat.getDescripcion()%></td>



                <td>
                    <div class="d-flex justify-content-center">
                        <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/categorias/<%= cat.getIdCategoria()%>">
                            <input class="btn btn-info" type="submit" value="DETALLES" />
                        </form>
                        <form class="me-3" action="${pageContext.request.contextPath}/tiendaDigital/categorias/editar/<%= cat.getIdCategoria()%>">
                            <input class="btn btn-warning" type="submit" value="EDITAR" />
                        </form>
                        <form action="${pageContext.request.contextPath}/tiendaDigital/categorias/borrar/" method="post">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= cat.getIdCategoria()%>"/>
                            <input class="btn btn-danger" type="submit" value="ELIMINAR" />
                        </form>
                    </div>
                </td>



            </tr>

            <%
                }
            } else {
            %>

            <p>No hay registros de categoria</p>

            <% } %>

            </tbody>
        </table>
    </div>
    <%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
