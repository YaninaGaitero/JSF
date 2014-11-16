/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAOs;
import Modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
/**
 *
 * @author alumno
 */
public class DAOProductos 
extends BBDD
{
    Statement ConsultaProductos;
    public DAOProductos() throws Exception
    {
        super();
        
    }
    
    public Hashtable ListaProductos ()
    {
        Hashtable Products=null;
        try {
        Products = new Hashtable();
        String sentencia = "select * from productos";
        ConsultaProductos = getConexion().createStatement();
        ResultSet rs = ConsultaProductos.executeQuery(sentencia);
        while (rs.next())
        {
            Producto add=new Producto(rs.getInt("id_producto"),
                                      rs.getString("nombre"),
                                      rs.getDouble("precio"),
                                      rs.getBoolean("deAlta"));
            Products.put(add.getId_producto(), add);
        }
        ConsultaProductos.close();
        return Products;
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
            return Products;
        }
    }
}
