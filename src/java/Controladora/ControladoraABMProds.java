/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;
import BD.*;
import Beans.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author usuario
 */
public class ControladoraABMProds 
{
    DatosProductos ConexionProductos;
    
    private String AggNombre;
    private String AggDescripcion;
    private int AggStock;
    private float AggPrecio;
    private int AggEstado;
    
    private Producto adding;
    private Producto modify;
    
    private int SelectID;
    private boolean MustStock;
    private boolean MustPrecio;
    
    public ControladoraABMProds() throws Exception 
    {
        ConexionProductos = new DatosProductos();
    }
    
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
        try 
        {
            String answer="altaprod";
            //El Codigo de Ingreso de Productos.
            Producto add = new Producto(0,getAdding().getNombre(),getAdding().getDescripcion(),getAdding().getStock(),getAdding().getPrecio(),getAdding().getEstado());
            ConexionProductos.altaProducto(add);
            return answer;
        } 
        catch (Exception ex) {
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
            if (!"".equals(modify.getNombre()))
            {
                modif.setNombre(modify.getNombre());
            }
            if (!"".equals(modify.getDescripcion()))
            {
                modif.setDescripcion(modify.getDescripcion());
            }
            if (MustStock)
            {
                modif.setStock(modify.getStock());
            }
            if (MustPrecio)
            {
                modif.setPrecio(modify.getPrecio());
            }
            if (modify.getEstado()!=2)
            {
                modif.setEstado(modify.getEstado());
            }
            ConexionProductos.modificarProducto(modif);
            return answer;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
        finally {
            modify = new Producto();
        }
    }

    public String getAggNombre() {
        return AggNombre;
    }

    public void setAggNombre(String AggNombre) {
        this.AggNombre = AggNombre;
    }

    public String getAggDescripcion() {
        return AggDescripcion;
    }

    public void setAggDescripcion(String AggDescripcion) {
        this.AggDescripcion = AggDescripcion;
    }

    public int getAggStock() {
        return AggStock;
    }

    public void setAggStock(int AggStock) {
        this.AggStock = AggStock;
    }

    public float getAggPrecio() {
        return AggPrecio;
    }

    public void setAggPrecio(float AggPrecio) {
        this.AggPrecio = AggPrecio;
    }

    public int getAggEstado() {
        return AggEstado;
    }

    public void setAggEstado(int AggEstado) {
        this.AggEstado = AggEstado;
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
    

    public boolean isMustStock() {
        return MustStock;
    }

    public void setMustStock(boolean MustStock) {
        this.MustStock = MustStock;
    }

    public boolean isMustPrecio() {
        return MustPrecio;
    }

    public void setMustPrecio(boolean MustPrecio) {
        this.MustPrecio = MustPrecio;
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
