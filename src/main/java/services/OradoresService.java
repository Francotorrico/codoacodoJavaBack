/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.google.gson.Gson;
import daos.OradorDaoMysql;
import java.sql.SQLException;
import java.util.List;
import models.Persona;
import models.Result;

/**
 *
 * @author franc
 */
public class OradoresService {
    private final OradorDaoMysql DAO=new OradorDaoMysql();
    private final Gson GSON= new Gson();
    List oradores;
    
    
    public String getOrador() throws SQLException{
        oradores = DAO.getOradores();
        Gson f=new Gson();
        String result = f.toJson(oradores);
        
        System.out.println("En services Orador:  "+result);
        return result;
    }
    
        public String postOrador(String orador) throws SQLException{
        Gson f=new Gson();
        Gson resultReturn=new Gson();
        Persona newOrador = f.fromJson(orador,Persona.class);
        
        boolean error = DAO.postOrador(newOrador);
        
        Result result = new Result(error);
        return resultReturn.toJson(result);
    }
    
     public String deleteOrador(String path) throws SQLException{
        System.out.println(path); // "/22" ejemplo -> luego parseo
        String filter= path.contains("/") ? path.substring(1): path;
        int id= Integer.parseInt(filter);
        int error = DAO.deleteOrador(id);
        System.out.println("SERVice "+ error);
        Result result = new Result(error ==0);
        return GSON.toJson(result);
    }
}
