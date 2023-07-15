
package services;



import com.google.gson.Gson;
import daos.TicketsDaoMysql;
import java.sql.SQLException;
import java.util.List;
import models.Result;
import models.Ticket;

public class TicketsService {
    //conexion con nuestro DAOS
    private final TicketsDaoMysql DAO=new TicketsDaoMysql();
    private final Gson GSON= new Gson();
    List tickets;
    
    public String getTickets() throws SQLException{
        tickets = DAO.getTickets();
        Gson f=new Gson();
        String result = f.toJson(tickets);
        
        //System.out.println("En services "+result);
        return result;
    }
    
    public String postTicket(String ticket) throws SQLException{
        Gson f=new Gson();
        Gson resultReturn=new Gson();
        Ticket newTicket = f.fromJson(ticket,Ticket.class);
        
        boolean error = DAO.postTicket(newTicket);
        
        Result result = new Result(error);
        return resultReturn.toJson(result);
    }
    
    public String modifyTicket(String ticket) throws SQLException{
        Ticket newTicket=GSON.fromJson(ticket, Ticket.class);
        //(String)
        boolean error= DAO.modifyTicket(newTicket);      
        Result result = new Result(error);
        return GSON.toJson(result);
    }
    public String deleteTicket(String path) throws SQLException{
        System.out.println(path); // "/22" ejemplo -> luego parseo
        String filter= path.contains("/") ? path.substring(1): path;
        int id= Integer.parseInt(filter);
        int error = DAO.deleteTicket(id);
        System.out.println("SERVice "+ error);
        Result result = new Result(error ==0);
        return GSON.toJson(result);
    }
}
