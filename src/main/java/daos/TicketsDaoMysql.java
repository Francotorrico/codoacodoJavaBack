
package daos;


import config.Conexion;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import models.Ticket;

public class TicketsDaoMysql {
    //static
    public static TicketsDaoMysql instance = null;
    
    public static TicketsDaoMysql getInstance(){
        if(instance==null){
            instance= new TicketsDaoMysql();
        }
        return instance;
    }
    //instancias
    Conexion con = new Conexion();
    Connection connect; 
    PreparedStatement ps;
    ResultSet rs;
    
    public LinkedList getTickets() throws SQLException{
        connect=con.getConnection();
        LinkedList l = new LinkedList();
        ps=connect.prepareStatement("Select * from tickets;");  
        rs=ps.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String nombre= rs.getString("nombre");
            String apellido= rs.getString("apellido");
            String correo= rs.getString("correo");
            int cantidad = rs.getInt("cantidad");
            String categoria= rs.getString("categoria");
            
            Ticket ticket = new Ticket(id, cantidad, nombre, apellido, correo, categoria);
            l.add(ticket);
            System.out.println(ticket);
        }
        
        
        return l;
    }
    
    
    public Boolean postTicket(Ticket ticket) throws SQLException{
        try {
            connect=con.getConnection();
            ps= connect.prepareStatement("INSERT INTO tickets(nombre,apellido,correo,cantidad,categoria) values(?,?,?,?,?);");
            //valores a reemplazar en incoginitas
            //agrego a los indices (1,nombre)
            ps.setString(1, ticket.getNombre());
            ps.setString(2, ticket.getApellido());
            ps.setString(3, ticket.getCorreo());
            ps.setInt(4, ticket.getCantidad());
            ps.setInt(5, ticket.getCategoria().getCodigo());
            ps.execute();
            return false;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return true; // si hay error me da true
        }
        
    }
    
    
    public Boolean modifyTicket(Ticket ticket) throws SQLException{
        connect=con.getConnection();
        try{
            ps = connect.prepareStatement("UPDATE tickets SET nombre =?, apellido=?,correo=?, cantidad=?, categoria=? WHERE id=?;");
            ps.setString(1, ticket.getNombre());
            ps.setString(2, ticket.getApellido());
            ps.setString(3, ticket.getCorreo());
            ps.setInt(4, ticket.getCantidad());
            ps.setInt(5, ticket.getCategoria().getCodigo());
            ps.setInt(6, ticket.getId());
            ps.execute();
            return false;
        }catch(SQLException e){
            System.out.println(e.toString());
            return true;
        }
       
    }
    public int deleteTicket(int id) throws SQLException{
            connect=con.getConnection();
            ps = connect.prepareStatement("DELETE FROM tickets WHERE id=?;");
            ps.setInt(1,id);
            return ps.executeUpdate();
           
       
    }
}
