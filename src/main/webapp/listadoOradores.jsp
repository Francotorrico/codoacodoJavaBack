<%-- 
    Document   : listadoOradores
    Created on : 15 jul. 2023, 05:23:16
    Author     : franc
--%>

<%@page import="models.Persona"%>
<%@page import="java.util.LinkedList"%>
<%@page import="daos.OradorDaoMysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

        <title>Listado de solicitudes oradores</title>
    </head>
    <body>
       
        <table class="table table-striped">
            
             <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Tema</th>       
                </tr>                 
            </thead>
             <tbody>
                <% 
                    OradorDaoMysql dao= OradorDaoMysql.getInstance();
                    //triga la instancia de daos
                    LinkedList<Persona> oradores=dao.getOradores();
                    //crea lista tickets-> almacena tickets de la base daos
                   
                    for(Persona persona : oradores){
                      
                %>
                
                <tr>
                    <td><%= persona.getId() %></td>
                    <td><%= persona.getNombre() %></td>
                    <td><%= persona.getApellido() %></td>
                    <td><%= persona.getTema()%></td>
                    
                    <td>
                        <button class="btn btn-secondary" onclick="deleteOrador(<%= persona.getId() %>)">Borrar</button>
                    </td>
                    
                    
                </tr>
            
            
                <%
                    }
                %>
            </tbody>
            <tfoot>
                <tr>
                     <td>Personas que enviaron sol. de orador: </td>
                    <td><b><%=  oradores.size() %></b></td>
                </tr>
            </tfoot>
            
        </table>
        <script src="./../js/jsp.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    </body>
</html>
