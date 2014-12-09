/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladora;

import BD.DaoProductos;
import Beans.Compra;
import Beans.DetalleCompra;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Yanina
 */
@ManagedBean
@RequestScoped
public class ControladoraFactura {
    private Hashtable listaProductos;
    private DetalleCompra det= new DetalleCompra();
    private Compra c= new Compra();
    DaoProductos daoProductos;
    
    public void agregarAlCarrito(DetalleCompra d) {
        if (getListaProductos() == null) {
            setListaProductos(new Hashtable());
        }
        d.setCantidad(det.getCantidad());
        if (!existeDetalleConEsteID(d.getIdProd())) {//no existe ese prod en esa fact lo agrega
            listaProductos.put(det.getIdProd(), det);
        } else {// si esta lo borra y lo agrega sumandole la cant
            DetalleCompra oDet = (DetalleCompra) listaProductos.get(d.getIdProd());
            listaProductos.remove(d.getIdProd());
            oDet.setCantidad(oDet.getCantidad() + d.getCantidad());
            listaProductos.put(d.getIdProd(), oDet);
        }
        
    }
    
    private Boolean existeDetalleConEsteID(int idProd ){
        Boolean rta = false;
        DetalleCompra oDet= (DetalleCompra) listaProductos.get(idProd);
        if(oDet!=null){
            rta=true;
        }return rta;
    }

    
    public ControladoraFactura() {
        try {
            daoProductos= new DaoProductos();
        } catch (Exception ex) {
            Logger.getLogger(ControladoraFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Hashtable getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Hashtable listaProductos) {
        this.listaProductos = listaProductos;
    }

    public DetalleCompra getDet() {
        return det;
    }

    public void setDet(DetalleCompra det) {
        this.det = det;
    }

    public Compra getC() {
        return c;
    }

    public void setC(Compra c) {
        this.c = c;
    }
    
}
