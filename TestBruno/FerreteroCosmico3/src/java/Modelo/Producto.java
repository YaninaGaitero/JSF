/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Producto 
{
    private int id_producto;
    private String nombre;
    private double precio;
    private int stock;
    private boolean deAlta;

    public Producto(String nombre, double precio, boolean deAlta) {
        this.nombre = nombre;
        this.precio = precio;
        this.deAlta = deAlta;
    }

    public Producto(int id_producto, String nombre, double precio, boolean deAlta) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.deAlta = deAlta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDeAlta() {
        return deAlta;
    }
    public void setDeAlta(boolean deAlta) {
        this.deAlta = deAlta;
    }
    
    
}
