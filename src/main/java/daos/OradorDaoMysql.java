/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import models.Persona;

/**
 *
 * @author franc
 */
public class OradorDaoMysql {
    public static OradorDaoMysql instance = null;
    
    public static OradorDaoMysql getInstance(){
        if(instance==null){
            instance= new OradorDaoMysql();
        }
        return instance;
    }
    //instancias
    Conexion con = new Conexion();
    Connection connect; 
    PreparedStatement ps;
    ResultSet rs;
    public LinkedList getOradores() throws SQLException{
        connect=con.getConnection();
        LinkedList l = new LinkedList();
        ps=connect.prepareStatement("Select * from oradores;");  
        rs=ps.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String nombre= rs.getString("nombre");
            String apellido= rs.getString("apellido");
            String tema= rs.getString("tema");
          
            
            Persona orador = new Persona(id, nombre, apellido, tema);
            l.add(orador);
            //System.out.println(orador);
        }
        
        
        return l;
    }
    
    
    
    public Boolean postOrador(Persona orador) throws SQLException{
        try {
            connect=con.getConnection();
            ps= connect.prepareStatement("INSERT INTO oradores(nombre,apellido,tema) values(?,?,?);");
            //valores a reemplazar en incoginitas
            //agrego a los indices (1,nombre)
            ps.setString(1, orador.getNombre());
            ps.setString(2, orador.getApellido());
            ps.setString(3, orador.getTema());
            ps.execute();
            return false;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return true; // si hay error me da true
        }
        
    }
    
    public int deleteOrador(int id) throws SQLException{
            connect=con.getConnection();
            ps = connect.prepareStatement("DELETE FROM oradores WHERE id=?;");
            ps.setInt(1,id);
            return ps.executeUpdate();
           
       
    }
}
