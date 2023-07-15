/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author franc
 */
public class Persona {
    
    private int id;
    private String nombre;
    private String apellido;
    private String tema;

    public Persona(String nombre, String apellido, String tema) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tema = tema;
    }

    public Persona(int id, String nombre, String apellido, String tema) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tema = tema;    
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTema() {
        return tema;
    }
    
}
