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
    
    
}
