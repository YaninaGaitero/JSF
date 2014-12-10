/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Beans.Compra;
import Beans.DetalleCompra;
import Beans.Producto;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author alumno
 */
public class DaoFacturas extends BBDD{
    
    public DaoFacturas() throws Exception 
    {
    }
    
    public String armaInFiltro(Hashtable listaCompras) {
        String inCondition = "(";
        Enumeration e = listaCompras.elements();
        while (e.hasMoreElements()) {
            Compra comp = (Compra) e.nextElement();
            inCondition += comp.getIdCompra() + ",";
        }
        return inCondition.substring(0, inCondition.length() - 1) + ")";
    }
    
    
    public Double getTotal(int idCompra) throws Exception {
        Double total = 0D;
        Hashtable detalles = TraerDetallesCliente(idCompra);
        Enumeration e = detalles.elements();
        while (e.hasMoreElements()) {
            DetalleCompra det = (DetalleCompra) e.nextElement();
            total += (det.getPrecio() * det.getCantidad());
        }
        return total;
    }
    
    
    
    public Hashtable TraerDetallesCliente(int id) throws Exception {
        try {
            Conectar();
            Hashtable tabla = new Hashtable();
            String sql = "select * from detallecompra where id_compra = '" + id + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int det = rows.getInt("id_detalle");
                int comp = rows.getInt("id_compra");
                float prec = rows.getFloat("precio");
                int idprod = rows.getInt("id_producto");
                int cant = rows.getInt("cantidad");
                DetalleCompra aux = new DetalleCompra(comp, prec, idprod, cant);
                aux.setIdDetalle(det);
                tabla.put(aux.getIdProd(), aux);
            }
            return tabla;
        } finally {
            Desconectar();
        }
        
        

    }
    
   

    public Hashtable traerComprasPendientes () throws Exception{
        try {
            Conectar();
            Hashtable tabla = new Hashtable();
            String sql = "SELECT * FROM compra   where estado = 1";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int idCompra = rows.getInt("id");
                int iduser = rows.getInt("idUsuario");
                Date fe = rows.getDate("fecha");
                int estado = rows.getInt("estado");
                Compra aux = new Compra(idCompra,iduser, fe, estado);
                tabla.put(aux.getIdCompra(), aux);
            }
            return tabla;
        } finally {
            Desconectar();
        }

    }
     public Compra traerCompraBYid (int id) throws Exception{
        try {
            Conectar();
            String sql = "SELECT * FROM compra   where id = "+ id;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            Compra aux;
            aux = new Compra();
            while (rows.next()) {
                int idCompra = rows.getInt("id");
                int iduser = rows.getInt("idUsuario");
                Date fe = rows.getDate("fecha");
                int estado = rows.getInt("estado");
                aux = new Compra(idCompra,iduser, fe, estado);
            }
            return aux;
        } finally {
            Desconectar();
        }

    }
     
        public Hashtable TraerCompras() throws Exception {
        Hashtable lista = new Hashtable();
        try {
            Conectar();

            String sql = "select * from compra";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int id_ = rows.getInt("id");
                int iduser = rows.getInt("idUsuario");
                Date fecha = rows.getDate("fecha");
               // float tot = rows.getFloat("total");
                int estado = rows.getInt("estado");
                Compra aux = new Compra(iduser, fecha, estado);
                aux.setIdCompra(id_);
                lista.put(aux.getIdCompra(), aux);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            Desconectar();
        }
        return lista;
    }
        
        
    public int GrabarCompra(int idUsuario) throws Exception {
// la compra siempre se graba con estado en 1 lo q quere decir que al momento de graqbarla va a quedar pendiente
        CallableStatement procedimiento;
        int ultimaFactura = 0;
        try {
            Conectar();
            procedimiento = getConexion().prepareCall("{call InsertarFacturaID( ? , ? , ?,? )}");
            procedimiento.registerOutParameter("lastID", Types.INTEGER);
            procedimiento.setDate("fec", new java.sql.Date(new java.util.Date().getTime()));
            procedimiento.setInt("usuario", idUsuario);
            procedimiento.setInt("est", 1);
            procedimiento.execute();
            ultimaFactura = procedimiento.getInt("lastID");
            procedimiento.close();
            return ultimaFactura;
        } finally {
            Desconectar();
        }
    }

    private void GrabarDetalle(int idCom, float precio, int Prod, int cantidad) throws Exception {
        try {
            Conectar();

            String sql = "insert into detallecompra ( id_compra, precio, id_producto, cantidad) values(" + idCom + ", " + precio + ", " + Prod + ", " + cantidad + ")";
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);

        } finally {
            Desconectar();
        }
    }
    public void grabaDetalles(Hashtable detalles, int idCompra) {
        Enumeration enu = detalles.elements();
        while (enu.hasMoreElements()) {
            DetalleCompra aux;
            aux = (DetalleCompra) enu.nextElement();
            try {
                GrabarDetalle(idCompra, aux.getPrecio(), aux.getIdProd(), aux.getCantidad());
            } catch (Exception ex) {
                
            }
        }
    }

   
    public Hashtable TraerComprasCliente(int id) throws Exception {
        try {
            Conectar();
            Hashtable lista = new Hashtable();
            String sql = "select * from compra where idUsuario = '" + id + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int id_ = rows.getInt("id");
                int iduser = rows.getInt("idUsuario");
                Date fecha = rows.getDate("fecha");;
                int estado = rows.getInt("estado");
                Compra aux = new Compra(iduser, fecha, estado);
                aux.setIdCompra(id_);
                lista.put(aux.getIdCompra(), aux);
            }
            return lista;
        } finally {
            Desconectar();
        }
    }
}
