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
    
    private String ModNombre;
    private String ModDescripcion;
    private int ModStock;
    private boolean MustStock;
    private float ModPrecio;
    private boolean MustPrecio;
    private int ModEstado;
    private int SelectID;

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
        try {
            String answer="altaprod";
            //El Codigo de Ingreso de Productos.
            Producto add = new Producto(0,AggNombre,AggDescripcion,AggStock,AggPrecio,AggEstado);
            ConexionProductos.altaProducto(add);
            return answer;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
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
            if (!"".equals(ModNombre))
            {
                modif.setNombre(ModNombre);
            }
            if (!"".equals(ModDescripcion))
            {
                modif.setDescripcion(ModDescripcion);
            }
            if (MustStock)
            {
                modif.setStock(ModStock);
            }
            if (MustPrecio)
            {
                modif.setPrecio(ModPrecio);
            }
            if (ModEstado!=2)
            {
                modif.setEstado(ModEstado);
            }
            ConexionProductos.modificarProducto(modif);
            return answer;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraABMProds.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
        finally {
            setModDescripcion("");
            setModEstado(2);
            setModNombre("");
            setModStock(0);
            setModPrecio(0);
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

    public String getModNombre() {
        return ModNombre;
    }

    public void setModNombre(String ModNombre) {
        this.ModNombre = ModNombre;
    }

    public String getModDescripcion() {
        return ModDescripcion;
    }

    public void setModDescripcion(String ModDescripcion) {
        this.ModDescripcion = ModDescripcion;
    }

    public int getModStock() {
        return ModStock;
    }

    public void setModStock(int ModStock) {
        this.ModStock = ModStock;
    }

    public float getModPrecio() {
        return ModPrecio;
    }

    public void setModPrecio(float ModPrecio) {
        this.ModPrecio = ModPrecio;
    }

    public int getModEstado() {
        return ModEstado;
    }

    public void setModEstado(int ModEstado) {
        this.ModEstado = ModEstado;
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
    
    
}
