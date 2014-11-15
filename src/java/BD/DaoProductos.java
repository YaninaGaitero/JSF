package BD;


import Beans.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public class DaoProductos extends BBDD{
 
    public DaoProductos()throws Exception{
    }
    public Hashtable TraerProductos() throws Exception {
        try {
            Conectar();
            Hashtable tabla = new Hashtable();
            String sql = "Select * from productos";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int id = rows.getInt("id");
                String nombre = rows.getString("nombre");
                String descrip = rows.getString("descripcion");
                int sto = rows.getInt("stock");
                float precio = rows.getFloat("precio");
                int estado = rows.getInt("estado");
                Producto aux = new Producto(id, nombre, descrip, sto, precio, estado);
                tabla.put(aux.getId(), aux);
            }
            return tabla;
        } finally {
            Desconectar();
        }

    }

    public Producto TraerProducto(int id) throws Exception {
        try {
            Conectar();
            Producto aux = new Producto();
            String sql = "select * from productos where id = " + id + "";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int id_ = rows.getInt("id");
                String nombre = rows.getString("nombre");
                String descrip = rows.getString("descripcion");
                int sto = rows.getInt("stock");
                float precio = rows.getFloat("precio");
                int estado = rows.getInt("estado");
                aux = new Producto(id_, nombre, descrip, sto, precio, estado);
            }
            return aux;
        } finally {
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

   
    
    
}
