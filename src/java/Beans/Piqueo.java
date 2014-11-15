/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import BD.DaoPiqueo;

/**
 *
 * @author Yanina
 */
public class Piqueo {
   private int idPiqueo;
   //private Hashtable listaCompras;
   private String descripcionProducto;
   private int cantidad;
   private int idProducto;
   private DaoPiqueo bd;

    
    public Piqueo(){}
    
    public Piqueo(int idPiqueo, String descripcion, int cantidad,int idProducto){
        this.idPiqueo=idPiqueo;
        this.descripcionProducto=descripcion;
        this.cantidad=cantidad;
        this.idProducto=idProducto;
    
    
    }

    public int getIdPiqueo() {
        return idPiqueo;
    }

    public void setIdPiqueo(int idPiqueo) {
        this.idPiqueo = idPiqueo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

/*    public Hashtable getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(Hashtable listaCompras) {
        this.listaCompras = listaCompras;
    }*/

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public DaoPiqueo getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DaoPiqueo();
        }
        return bd;
    }
}
