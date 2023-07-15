/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.OradoresService;

/**
 *
 * @author franc
 */
@WebServlet(name = "OradoresController", urlPatterns = {"/api/oradores","/api/oradores/*"})
public class OradoresController extends HttpServlet {

    OradoresService oradorService = new OradoresService();
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            //System.out.println("AAA");
            String orador = oradorService.getOrador();
            System.out.println("en controller "+ orador);
            enviar(response,orador);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
       try {
            String body = bodyToString(request.getInputStream()); // la clase privada creada
            System.out.println(body);
            String result = oradorService.postOrador(body);
            enviar(response, result);
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
           String result = oradorService.deleteOrador(path);
           enviar(resp, result);
       } catch (SQLException ex) {
           System.out.println(ex.toString());
       }
       
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
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
