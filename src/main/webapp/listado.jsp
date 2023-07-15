<%-- 
    Document   : listado
    Created on : 13 jul. 2023, 19:49:57
    Author     : franc
--%>

<%@page import="models.Ticket"%>
<%@page import="java.util.LinkedList"%>
<%@page import="daos.TicketsDaoMysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Listado de productos</title>
    </head>
    <body>
               
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Correo</th>
                    <th>Cantidad</th>
                    <th>Tipo</th>
                </tr>                 
            </thead>
            
            <tbody>
                <% 
                    TicketsDaoMysql dao= TicketsDaoMysql.getInstance();
                    //triga la instancia de daos
                    LinkedList<Ticket> tickets=dao.getTickets();
                    //crea lista tickets-> almacena tickets de la base daos
                    int ticketsVendidos=0;
                    for(Ticket t: tickets){
                        ticketsVendidos+=t.getCantidad();
                %>
                
                <tr>
                    <td><%= t.getId() %></td>
                    <td><%= t.getNombre() %></td>
                    <td><%= t.getApellido() %></td>
                    <td><%= t.getCorreo() %></td>
                    <td><%= t.getCantidad() %></td>
                    <td><%= t.getCategoria() %></td>
                    
                    <td>
                        <button class="btn btn-secondary" onclick="deleteTicket(<%= t.getId() %>)">Borrar</button>
                    </td>
                    
                    
                </tr>
            
            
                <%
                    }
                %>
            </tbody>
            <tfoot>
                <tr>
                    <td>Tickets vendidos:</td>
                    <td><b><%=  ticketsVendidos %></b></td>
                     <td>Personas que compraron: </td>
                    <td><b><%=  tickets.size() %></b></td>
                </tr>
            </tfoot>
        </table>
        
        <script src="./../js/jsp.js"></script>
                    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
