<%-- 
    Document   : listadoOradores
    Created on : 15 jul. 2023, 05:23:16
    Author     : franc
--%>

<%@page import="services.OradoresService"%>
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
        <header class="bg-black">
        
            <nav class="navbar navbar navbar-expand-sm nav--background align-content-sm-center ">
                <div class="container-fluid mx-5 justify-content-md-center">
                    
                  <a class=" navbar-brand text-light" href="#">
                    <img src="img/codoacodo.png" alt="Logo" width="80" height="45" class="d-inline-block">
                    Conf Bs As
                  </a>
    
                  <button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                  aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>
                  <div class="collapse navbar-collapse justify-content-end text-center justify-content-sm-end" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active text-light text-decoration-line-through" aria-current="page" href="#">La conferencia</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light opacity-50 text-decoration-line-through" href="#">Los oradores</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light opacity-50 text-decoration-line-through" href="#">El lugar y la fecha</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light opacity-50 text-decoration-line-through" href="#">Conviertete en orador</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-success fw-bold" href="controlador.html"><i class="bi bi-credit-card"></i> Volver Atrás</a>
                        </li>
                    </ul>
                </div>
                 
                
    
                </div>
    
    
            
    
              </nav>
    
    
    </header>
    <div class="d-flex align-items-center justify-content-center">
        <div>
          <h1 class="bg-secondary bg-opacity-75 m-sm-5">Lista de solicitud oradores:</h1>
          <!-- Otro contenido aquí -->
        </div>
      </div>
       
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
                    OradoresService o= new OradoresService();
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
        <button class="btn btn-secondary" onclick="function() { <%= o.getOrador() %> }">capturar tabla</button>

        <script src="./../js/jsp.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

    </body>
</html>
