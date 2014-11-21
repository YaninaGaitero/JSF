/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import BD.DaoFacturas;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 *
 * @author La Florida Cesped
 */
public class Compra {
    
    private int idCompra;
    private int idUsuario;
    private Date fecha;
    private int estado;
    private DaoFacturas bd;
    
    
    public Compra (){}
    
    public Compra ( int idu, Date fec, int estado)
    {
        this.idUsuario = idu;
        this.fecha = fec;
        this.setEstado(estado);
       
    }
    
    public Compra ( int idCompra,int idu, Date fec, int estado)
    {
        this.idUsuario = idu;
        this.fecha = fec;
        this.setEstado(estado);
        this.setIdCompra(idCompra);
       
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
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if(estado==0){
            this.estado = 1;
        }else{
            this.estado = estado;
        }
        
    }

   public DaoFacturas getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DaoFacturas();
        }
        return bd;
    }
}
