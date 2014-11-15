/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import BD.DaoFacturas;
import java.sql.Date;


public class DetalleCompra {
    
  private int idDetalle;
    private int idCompra;
    private float precio;
    private int idProd;
    private int cantidad;
    private DaoFacturas bd;
   
   
     public DetalleCompra (){}
     

    public DetalleCompra ( int idCom, float prec, int idProd, int cant)            
    {
        
        this.idCompra = idCom;
        this.precio = prec;
        this.idProd = idProd;
        this.cantidad = cant; 
        
       
    }

    /**
     * @return the idDetalle
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * @return the idCompra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the idProd
     */
    public int getIdProd() {
        return idProd;
    }

    /**
     * @param idProd the idProd to set
     */
    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   public DaoFacturas getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DaoFacturas();
        }
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(DaoFacturas bd) {
        this.bd = bd;
    }
    

}
