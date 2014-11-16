/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author Usuario
 */
public class Factura 
{
    private final int id_factura;
    private final DateTime fecha_venta;
    private final Usuario usuario_obj;
    private final Hashtable lineasFacturas;
    private final boolean temporal;

    public Factura(DateTime fecha_venta, Usuario usuario_id) 
    {
        this.lineasFacturas = new Hashtable();
        id_factura=0;
        this.fecha_venta = fecha_venta;
        this.usuario_obj = usuario_id;
        temporal = true;
    }

    public Factura(int id_factura, DateTime fecha_venta, Usuario usuario_id) {
        this.lineasFacturas = new Hashtable();
        this.id_factura = id_factura;
        this.fecha_venta = fecha_venta;
        this.usuario_obj = usuario_id;
        temporal = false;
    }
    
    public void AggLinea(LineaFactura lf)
    {
        if (temporal)
        {
            Enumeration lineas = this.lineasFacturas.elements();
            LineaFactura aux=null;
            while (lineas.hasMoreElements())
            {
                LineaFactura lin=(LineaFactura)lineas.nextElement();
                if (lf.getVenta().getId_producto()==lin.getVenta().getId_producto())
                {
                    aux=lin;    
                }
            }
            if (aux!=null)
            {
                lineasFacturas.remove(aux.getId_LF());
                lineasFacturas.put(lf.getId_LF(), lf);
            }
            else
            {
                lineasFacturas.put(lf.getId_LF(), lf);
            }
        }
        else
        {
            lineasFacturas.put(lf.getId_LF(), lf);
        }
    }
    
    public void BorrarLinea (int id_LF)
    {
        if (temporal)
        {
            Enumeration lineas = lineasFacturas.elements();
            while (lineas.hasMoreElements())
            {
                LineaFactura lf = (LineaFactura)lineas.nextElement();
                if (lf.getId_LF()==id_LF)
                {
                    lineasFacturas.remove(id_LF);
                }
            }
        }
    }
    
    public double TotalFactura ()
    {
        double total=0.0;
        Enumeration lineas = getLineasFacturas().elements();
        while (lineas.hasMoreElements())
        {
            LineaFactura lf = (LineaFactura)lineas.nextElement();
            total += lf.getSubtotal();
        }
        return total;
    }
    
    public int getId_factura() {
        return id_factura;
    }


    public DateTime getFecha_venta() {
        return fecha_venta;
    }


    public Usuario getUsuario() {
        return usuario_obj;
    }

    public Hashtable getLineasFacturas() {
        return lineasFacturas;
    }
    
    
}
