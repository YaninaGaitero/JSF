/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAOs;

import Modelo.Factura;
import Modelo.LineaFactura;
import Modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class DAOLinFact 
extends BBDD
{

    DAOProductos listaaux;
    PreparedStatement ConsultaLineas;
    PreparedStatement InsertarLineas;
    public DAOLinFact() throws Exception 
    {
        super();
        
        
    }
    
    public Hashtable LineasXFacturas (Factura Compra)
            throws Exception
    {
        this.listaaux = new DAOProductos();
        Hashtable productos=listaaux.ListaProductos();
        Hashtable LFact = new Hashtable();
        ConsultaLineas = getConexion().prepareStatement(
                                        "select * from lineasfacturas where factura_pk= ? ");
       
        ConsultaLineas.setInt(1, Compra.getId_factura());
        ResultSet rs = ConsultaLineas.executeQuery();
        while (rs.next())
        {
            Producto linea = (Producto)productos.get(rs.getInt("producto_pk"));
            //System.out.println(linea.getNombre());
            LineaFactura lf = new LineaFactura(rs.getInt("id_LF"),
                                               linea,
                                               rs.getDouble("precio"),
                                               rs.getInt("cantidad"),
                                               rs.getInt("factura_pk"));
            LFact.put(lf.getId_LF(), lf);
            
        }
        ConsultaLineas.close();
        return LFact;
    }
    
    public void InsertarLinea (LineaFactura Ins, int Factura_id)
    {
        try 
        {
            InsertarLineas = getConexion().prepareStatement("insert into lineasfacturas (cantidad,factura_pk,precio,producto_pk) "+
                    "values( ? , ? , ? , ? )");
            InsertarLineas.setInt(1, Ins.getCantidad());
            InsertarLineas.setInt(2, Factura_id);
            InsertarLineas.setDouble(3, Ins.getPrecio());
            InsertarLineas.setInt(4, Ins.getVenta().getId_producto());
            InsertarLineas.executeUpdate();
            InsertarLineas.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DAOLinFact.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
