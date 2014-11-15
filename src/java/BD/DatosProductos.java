/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Beans.Producto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yanina
 */
public class DatosProductos extends BBDD{
    Producto producto= new Producto();
    
    public DatosProductos()throws Exception
    {
        
    }
    
    public Hashtable ListaProductos()
    {
        try 
        {
            Hashtable lista = new Hashtable();
            Conectar();
            String sql = "select * from productos";
            PreparedStatement sentencia;
            sentencia = CrearSentencia(sql);
            ResultSet rows = Consultar(sentencia);
            while (rows.next())
            {
                Producto add = new Producto(rows.getInt("id"),
                                            rows.getString("nombre"),
                                            rows.getString("descripcion"),
                                            rows.getInt("stock"),
                                            rows.getFloat("precio"),
                                            rows.getInt("estado"));
                lista.put(add.getId(), add);
            }
            return lista;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(DatosProductos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally
        {
            Desconectar();
        }
        
    }

        public String TraerNombreProducto(int id) throws Exception {
        try {
            String nombre = null;
            Conectar();
            String sql = "select nombre from productos where id = '" + id + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                nombre = rows.getString("nombre");
            }
            return nombre;

        } finally {
            Desconectar();
        }

    }
    public String TraerNombreProducto1(int id) throws Exception {
        try {
            String nombre = null;
            String sql = "select nombre from productos where id = '" + id + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                nombre = rows.getString("nombre");
            }
            return nombre;

        } finally {
        }

    }
    public void altaProducto(Producto p) throws Exception {
        try {
            Conectar();

            String sql = "INSERT INTO productos( nombre, descripcion, estado, stock, precio) VALUES ('"+ p.getNombre() + "','" + p.getDescripcion()+  "',"+p.getEstado()+","+p.getStock()+","+p.getPrecio()+")";
            //String sql = "INSERT INTO productos(id, nombre, descripcion, estado, stock, precio) VALUES (0,'"+ p.getNombre() + "','" + p.getDescripcion()+  "',"+p.getEstado()+","+p.getStock()+","+p.getPrecio()+")";
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);

        } finally {
            Desconectar();
        }
    }
    
    public void modificarProducto(Producto p)throws Exception {
        try {
            Conectar();
            String sql = "UPDATE productos SET nombre='"+ p.getNombre()+"',descripcion='"+p.getDescripcion()+"',estado="+p.getEstado()+",stock="+p.getStock()+",precio="+p.getPrecio()+" WHERE id="+p.getId();
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);
        } finally {
            Desconectar();
        }
    }
    public void bajaProducto(Producto p)throws Exception {
        try {
            Conectar();
            String sql = "UPDATE productos SET estado=2"+p.getEstado()+" WHERE id="+p.getId();
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);
        } finally {
            Desconectar();
        }
    }
}
