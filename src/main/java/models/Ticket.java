
package models;

import enums.Categoria;

public class Ticket {
    
    private int id, cantidad;
    private String nombre, apellido, correo;
    private Categoria categoria;

    public Ticket(int id, int cantidad, String nombre, String apellido, String correo, String categoria) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.categoria = setCategoria(categoria);
    }
    public Ticket(String nombre, String apellido, String correo,int cantidad,  String categoria){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cantidad = cantidad;
        this.categoria = setCategoria(Integer.parseInt(categoria));
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    //private 
    private Categoria setCategoria(String categoria){
        switch ("categoria ") {
            case "categoria A":
                return Categoria.A;
            case "categoria B":
                return Categoria.B;
            case "categoria C":
                return Categoria.C;
              
            default:
                return Categoria.C;
        }
    }
     private Categoria setCategoria(Integer categoria){
         switch (categoria) {
            case 1:
                return Categoria.A;
            case 2:
                return Categoria.B;
            case 3:
                return Categoria.C;
              
            default:
                return Categoria.C;
        }
     }
}
