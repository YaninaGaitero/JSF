/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author alumno
 */
public class LineaFactura 
{
    private int id_LF;
    private Producto venta;
    private double precio;
    private int cantidad;
    private int factura_id;

    public LineaFactura(int id_LF, Producto venta, double precio, int cantidad, int factura_id) {
        this.id_LF = id_LF;
        this.venta = venta;
        this.precio = precio;
        this.cantidad = cantidad;
        this.factura_id = factura_id;
    }

    public LineaFactura(int id_LF,Producto venta, double precio, int cantidad) {
        this.id_LF = id_LF;
        this.venta = venta;
        this.precio = venta.getPrecio();
        this.cantidad = cantidad;
        this.factura_id = 0;
    }

    

    public int getId_LF() {
        return id_LF;
    }

    public Producto getVenta() {
        return venta;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getFactura_id() {
        return factura_id;
    }
    
    public double getSubtotal()
    {
        double ret;
        ret=precio*(cantidad*1.0);
        return ret;
    }
    
}
