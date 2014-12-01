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
/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class carritoDeCompras 
{
    private DatosProductos ConexionProductos;
    private int selectProductoID;
    private Producto choose;
    private int cantidadIng;
    private ArrayList Detalles;

    /**
     * Creates a new instance of carritoDeCompras
     * @throws java.lang.Exception
     */
    public carritoDeCompras() throws Exception 
    {
        ConexionProductos = new DatosProductos();
    }
    
    public String AgregarDetalle()
    {
        String answer="carrito";
        Enumeration elist = ConexionProductos.ListaProductos().elements();
        while (elist.hasMoreElements())
        {
            Producto p = (Producto)elist.nextElement();
            if(p.getId()==selectProductoID)
                setChoose(p);
        }
        DetalleCompra dcadd = new DetalleCompra(0,choose.getPrecio(),selectProductoID,cantidadIng);
        Detalles.add(dcadd);
        return answer;
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
    
}
