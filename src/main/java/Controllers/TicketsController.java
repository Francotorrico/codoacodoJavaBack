
package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.TicketsService;

//recibe pedido de clientes

@WebServlet(name = "TicketsController", urlPatterns = {"/api/tickets", "/api/tickets/*"})
public class TicketsController extends HttpServlet {

   TicketsService ticketservice = new TicketsService();
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //response.sendRedirect("http://www.google.com");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TicketController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ticketController at" + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    /*
    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) throws ServletException, IOException {
        super.doGet(req, res); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        //res.sendRedirect("http://google.com");
        res.sendRedirect("http://www.google.com");
        
        /*
        try {
            //System.out.println("AAA");
            String tickets = ticketservice.getTickets();
            System.out.println("en controller "+ tickets);
            //enviar(res,tickets);
            //res.sendRedirect("http://google.com");
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }*/
   
    //}

    
    /*
    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        super.doPost(req, res); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        /*   
        try {
            System.out.println("AAA");
            String tickets = ticketservice.getTickets();
            //System.out.println("en controller "+ tickets);
            enviar(res,tickets);
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }*/
    //}

   
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
    
    
     @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        //super.doGet(request, response);
        //response.sendRedirect("http://google.com"); 
        //processRequest(request, response);
         try {
            System.out.println("AAA");
            String tickets = ticketservice.getTickets();
            System.out.println("en controller "+ tickets);
            enviar(response,tickets);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
      
        
       try {
            String body = bodyToString(request.getInputStream()); // la clase privada creada
            System.out.println(body);
            String result = ticketservice.postTicket(body);
            enviar(response, result);
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
        
    }

    @Override
    protected void doPut(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
    
       try {
           String body = bodyToString(req.getInputStream());
           String result = ticketservice.modifyTicket(body);
           enviar(resp, result);
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }

        
    }

    @Override
    protected void doDelete(HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
       try { 
           String path=req.getPathInfo();
           String result = ticketservice.deleteTicket(path);
           enviar(resp, result);
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
       
        
    }
    
    
    //luego conexion con servicios ticket

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
