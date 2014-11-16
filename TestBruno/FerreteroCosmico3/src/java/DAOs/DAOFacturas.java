/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAOs;
import Modelo.*;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class DAOFacturas
extends BBDD
{
    DAOLinFact ConexionLF;
    DAOUsuarios ConexionUsuarios;
    PreparedStatement ConsultaFacturas;
    CallableStatement procFactura;
    PreparedStatement UltimaCompra;
    
    public DAOFacturas() throws Exception
    {
        super();
        
        
    }
    
    public Hashtable FacturasXUsuario (Usuario buys)
    {
        try {
            Hashtable ListaFacturas = new Hashtable();
            this.ConexionUsuarios = new DAOUsuarios();
            this.ConexionLF = new DAOLinFact();
            ArrayList Usuarios = ConexionUsuarios.ListaUsuarios();
            
            ConsultaFacturas = getConexion().prepareStatement("select * from facturas "+
                         "where usuario_pk= ? ");
            ConsultaFacturas.setInt(1,buys.getId_usuario());
        
            ResultSet rs = ConsultaFacturas.executeQuery();
        
        while (rs.next())
        {
            int id_fact = rs.getInt("id_factura");
            DateTime creation = new DateTime(rs.getDate("FechaVenta"));
            //System.out.println(creation.TimeFormat("dd/MM/yyyy"));
            Usuario comprador = (Usuario)Usuarios.get(rs.getInt("usuario_pk"));
            Factura add = new Factura(id_fact,
                                      creation,
                                      comprador);
            Hashtable Lineas = ConexionLF.LineasXFacturas(add);
            Enumeration Lin = Lineas.elements();
            while (Lin.hasMoreElements())
            {
                LineaFactura lf = (LineaFactura)Lin.nextElement();
                //System.out.println(lf.getCantidad());
                if (add.getId_factura()==lf.getFactura_id())
                add.AggLinea(lf);
            }
            //System.out.println(add.getFecha_venta().TimeFormat("dd/MM/yyyy"));
            ListaFacturas.put(add.getId_factura(), add);
        }
            ConsultaFacturas.close();
            return ListaFacturas; 
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
            return null; 
        }
    }
    
    private int InsertarFactura (Factura compra)
    {
        int UltimaFactura=0;
        try 
        {
            procFactura = getConexion().prepareCall("call InsertarFacturaID( ? , ? , ? )");
            //java.sql.Date enter = new java.sql.Date(compra.getFecha_venta().getFecha().getTime());
            procFactura.registerOutParameter("lastID", Types.INTEGER);
            //procFactura.setDate("fecha", enter);
            procFactura.setString("fecha", compra.getFecha_venta().TimeFormat("yyyy-MM-dd hh:mm:ss"));
            procFactura.setInt(3, compra.getUsuario().getId_usuario());
            procFactura.execute();
            UltimaFactura = procFactura.getInt("lastID");
            procFactura.close();
            this.ConexionLF = new DAOLinFact();
            Enumeration lineas = compra.getLineasFacturas().elements();
            while (lineas.hasMoreElements())
            {
                LineaFactura lf = (LineaFactura)lineas.nextElement();
                this.ConexionLF.InsertarLinea(lf,UltimaFactura);
            }
            return UltimaFactura;
        }
        catch (SQLException SE)
        {
            System.out.println(SE.getNextException());
            return UltimaFactura;
        } catch (Exception ex) 
        {
            Logger.getLogger(DAOFacturas.class.getName()).log(Level.SEVERE, null, ex);
            return UltimaFactura;
        }
    }
    
    public Factura CompraRealizada (Factura buy)
    {
        
        try 
        {
            this.ConexionUsuarios = new DAOUsuarios();
            ArrayList Users = this.ConexionUsuarios.ListaUsuarios();
            
            Usuario Comp = (Usuario)Users.get(buy.getUsuario().getId_usuario());
            int factura = InsertarFactura(buy);
            Factura success = null;
            this.ConexionLF = new DAOLinFact();
            UltimaCompra = getConexion().prepareStatement("select * from facturas where id_factura = ? ");
            UltimaCompra.setInt(1, factura);
            ResultSet rs = UltimaCompra.executeQuery();
            while (rs.next())
            {
                DateTime dia = new DateTime(rs.getDate("FechaVenta"));
                success = new Factura(rs.getInt("id_factura"),
                                      dia,
                                      Comp);
                Enumeration Lin = ConexionLF.LineasXFacturas(success).elements();
                while (Lin.hasMoreElements())
                {
                    LineaFactura lf = (LineaFactura)Lin.nextElement();
                    //System.out.println(lf.getCantidad());
                    if (success.getId_factura()==lf.getFactura_id())
                    success.AggLinea(lf);
                }   
            }
            UltimaCompra.close();
            return success;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DAOFacturas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DAOFacturas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
