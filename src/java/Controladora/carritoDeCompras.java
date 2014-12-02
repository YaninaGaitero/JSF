/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import BD.*;
import Beans.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class carritoDeCompras {

    private DatosProductos ConexionProductos;
    private int selectProductoID;
    private Producto choose;
    private int cantidadIng;
    private ArrayList Detalles = new ArrayList();

    /**
     * Creates a new instance of carritoDeCompras
     *
     * @throws java.lang.Exception
     */
    public carritoDeCompras() throws Exception {
        ConexionProductos = new DatosProductos();
    }

    public String agregarDetalle() {
        String answer = "carrito";
        Enumeration elist = ConexionProductos.ListaProductos().elements();
        while (elist.hasMoreElements()) {
            Producto p = (Producto) elist.nextElement();
            if (p.getId() == selectProductoID) {
                setChoose(p);
            }
        }
        DetalleCompra dcadd = new DetalleCompra(0, choose.getPrecio(), selectProductoID, cantidadIng);
        if (!getDetalles().isEmpty()) {
            Iterator verif = getDetalles().iterator();
            DetalleCompra v = null;
            while (verif.hasNext()) {
                DetalleCompra i = (DetalleCompra) verif.next();
                if (dcadd.getIdProd() == i.getIdProd()) {
                    v = i;
                }
            }
            if (v != null) {
                getDetalles().remove(v);
            }
        }
        getDetalles().add(dcadd);
        return answer;
    }
    
    public String borrarDetalle(int ProdID)
    {
        String answer="carrito";
        DetalleCompra drop = null;
        Iterator delete = getDetalles().iterator();
        while (delete.hasNext())
        {
            DetalleCompra d = (DetalleCompra)delete.next();
            if (d.getIdProd()==ProdID)
                drop = d;
        }
        if (drop!=null)
        {
            getDetalles().remove(drop);
        }
        return answer;
    }
    
    public float totalCarrito()
    {
        float result=0f;
        if (!getDetalles().isEmpty())
        {
            Iterator ite = getDetalles().iterator();
            while (ite.hasNext())
            {
                DetalleCompra dc = (DetalleCompra)ite.next();
                result += dc.getPrecio()*(dc.getCantidad()*1.0f);
            }
        }
        return result;
    }
    
    public boolean hayCarrito()
    {
        return !getDetalles().isEmpty();
    }
    
    public int getSelectProductoID() {
        return selectProductoID;
    }

    public void setSelectProductoID(int selectProductoID) {
        this.selectProductoID = selectProductoID;
    }

    public Producto getChoose() {
        return choose;
    }

    public void setChoose(Producto choose) {
        this.choose = choose;
    }

    public int getCantidadIng() {
        return cantidadIng;
    }

    public void setCantidadIng(int cantidadIng) {
        this.cantidadIng = cantidadIng;
    }

    public ArrayList getDetalles() {
        return Detalles;
    }

    public void setDetalles(ArrayList Detalles) {
        this.Detalles = Detalles;
    }

}
