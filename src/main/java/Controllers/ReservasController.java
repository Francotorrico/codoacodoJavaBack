/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import daos.TicketsDaoMysql;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Ticket;
import services.TicketsService;

/**
 *
 * @author franc
 */
@WebServlet(name = "ReservasController", urlPatterns = {"/api/reservas"})
public class ReservasController extends HttpServlet {

    private final TicketsService ticketsService= new TicketsService();
    private final TicketsDaoMysql DAO = TicketsDaoMysql.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher= req.getRequestDispatcher("./../listado.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
   
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        String categoria = req.getParameter("categoria");
        

        Ticket ticket= new Ticket(nombre, apellido, correo, cantidad, categoria);
        System.out.println(ticket.getNombre());
        System.out.println(ticket.getApellido());
        System.out.println(ticket.getCorreo());
        System.out.println(ticket.getCantidad());
        System.out.println(ticket.getCategoria());
        try {
            boolean error = DAO.postTicket(ticket);
            if(!error){ // si no hay error
                resp.sendRedirect("./../exito.html");
            }else{ // si hay error
                resp.sendRedirect("./../error.html");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            resp.sendRedirect("./../error.html");
        }
    }
    
    
    
    @Override
    protected void doPut(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
    
       try {
           String body = bodyToString(req.getInputStream());
           String result = ticketsService.modifyTicket(body);
           enviar(resp, result);
       } catch (SQLException ex) {
           System.out.println(ex.toString());
           resp.sendRedirect("./../error.html");
       }

        
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
    
        try {
            String id = bodyToString(req.getInputStream());
            String result = ticketsService.deleteTicket(id);
            enviar(resp, result);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            //resp.sendRedirect("./../error.html");
        }

        
    }
     private void enviar(HttpServletResponse res, String json) throws IOException{
        PrintWriter out = res.getWriter();      
        res.setContentType("application/json");//contenido de tipo
        res.setCharacterEncoding("UTF-8");//codificacion
        out.print(json);
        out.flush(); //refresco
    }
    
    //private
    private String bodyToString(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext()
                ? scanner.useDelimiter("\\A").next()
                :"";
    }
    
}
