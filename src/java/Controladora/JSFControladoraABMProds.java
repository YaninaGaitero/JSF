/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladora;

import BD.DatosProductos;
import Beans.Producto;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author alumno
 */
@ManagedBean
@RequestScoped
public class JSFControladoraABMProds {

    /**
     * Creates a new instance of JSFControladoraABMProds
     * @throws java.lang.Exception
     */
    public JSFControladoraABMProds() throws Exception 
    {
        ConexionProductos = new DatosProductos();
    }
    
    DatosProductos ConexionProductos;
    private Producto modify = new Producto();
    private Producto adding = new Producto();
    private int SelectID;
    
    public ArrayList listaProductos()
    {
        try 
        {
            ArrayList listado= new ArrayList();
            Enumeration data = ConexionProductos.ListaProductos().elements();
            while (data.hasMoreElements())
            {
                Producto agg = (Producto)data.nextElement();
                listado.add(agg);
            }
            return listado;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String altaProducto()
    {
        try {
            String answer="altaprod";
            //El Codigo de Ingreso de Productos.
            Producto add = new Producto(0,adding.getNombre(),adding.getDescripcion(),adding.getStock(),adding.getPrecio(),adding.getEstado());
            ConexionProductos.altaProducto(add);
            return answer;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }
    
    public void selectEvent(AjaxBehaviorEvent E)
    {
            Enumeration listu = ConexionProductos.ListaProductos().elements();
            while (listu.hasMoreElements())
            {
                Producto p = (Producto)listu.nextElement();
                if (p.getId()==SelectID)
                    setModify(p);
            }
    }
    
    public String modifProducto()
    {
        try {
            String answer="modifprod";
            Producto modif=null;
            Enumeration listu = ConexionProductos.ListaProductos().elements();
            while (listu.hasMoreElements())
            {
                Producto p = (Producto)listu.nextElement();
                if (p.getId()==SelectID)
                    modif = p;
            }
            modif.setNombre(modify.getNombre());
            modif.setDescripcion(modify.getDescripcion());
            modif.setStock(modify.getStock());
            modif.setPrecio(modify.getPrecio());
            modif.setEstado(modify.getEstado());
            ConexionProductos.modificarProducto(modif);
            return answer;
        } 
        catch (Exception ex) {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }

    public int getSelectID() {
        return SelectID;
    }

    public void setSelectID(int SelectID) {
        this.SelectID = SelectID;
    }

    public Producto getModify() {
        return modify;
    }

    public void setModify(Producto Mod) {
        this.modify = Mod;
    }
    

    /**
     * @return the adding
     */
    public Producto getAdding() {
        return adding;
    }

    /**
     * @param adding the adding to set
     */
    public void setAdding(Producto adding) {
        this.adding = adding;
    }
    
}
