/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Beans.Compra;
import Beans.DetalleCompra;
import Beans.Producto;
import Beans.Usuario;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class DatosUsuario extends BBDD {

    public DatosUsuario() throws Exception 
    {

        super();

    }
    

    public Usuario Logueo(String usuario, String pass) throws Exception {
        try {
            Conectar();
            Usuario aux = new Usuario();

            String sql = "SELECT * from usuarios where nombre='" + usuario + "' and pass='" + pass + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);

            if (rows.next()) {
                aux.setId(rows.getInt("id"));
                aux.setApellido(rows.getString("apellido"));
                aux.setNombre(rows.getString("nombre"));
                aux.setPass(rows.getString("pass"));
                aux.setDireccion(rows.getString("direccion"));
                aux.setTelefono(rows.getInt("telefono"));
                aux.setNivel(rows.getInt("nivel"));
                aux.setDocumento(rows.getInt("documento"));
                return aux;
            }
            return null;
        } finally {
            Desconectar();
        }
    }

    public Hashtable TrearUsuariosT() throws Exception 
    {
        try {
            Conectar();

            Hashtable Lista = new Hashtable();

            String sql = "Select * from usuarios";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int id = (rows.getInt("id"));
                String apellido = (rows.getString("apellido"));
                String nombre = (rows.getString("nombre"));
                String pass = (rows.getString("pass"));
                String direcc = (rows.getString("direccion"));
                int tel = (rows.getInt("telefono"));
                int nivel = (rows.getInt("nivel"));
                int doc = (rows.getInt("documento"));
                int est = (rows.getInt("estado"));
                Usuario aux = new Usuario(id, nombre, apellido, doc, pass, tel, direcc);

                aux.setNivel(nivel);
                aux.setEstado(est);
                Lista.put(aux.getId(), aux);

            }
            return Lista;
        } 
        finally {
            Desconectar();
        }
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

    public Usuario TraerUsuario(String nomb) throws Exception {
        try {
            Conectar();
            Usuario aux = new Usuario();
            String sql = "select * from usuarios where nombre = '" + nomb + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                int id_ = (rows.getInt("id"));
                String apellido = (rows.getString("apellido"));
                String nombre = (rows.getString("nombre"));
                String pass = (rows.getString("pass"));
                String direcc = (rows.getString("direccion"));
                int tel = (rows.getInt("telefono"));
                int nivel = (rows.getInt("nivel"));
                int doc = (rows.getInt("documento"));
                int est = (rows.getInt("estado"));
                aux = new Usuario(id_, nombre, apellido, doc, pass, tel, direcc);
                aux.setNivel(nivel);
                aux.setEstado(est);
            }
            return aux;
        } finally {
            Desconectar();
        }
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
            procedimiento.setInt("EST", 1);
            procedimiento.execute();
            ultimaFactura = procedimiento.getInt("lastID");
            procedimiento.close();
            return ultimaFactura;
        } finally {
            Desconectar();
        }
    }

    public void GrabarDetalle(int idCom, float precio, Producto Prod, int cantidad) throws Exception {
        try {
            Conectar();

            String sql = "insert into detallecompra ( id_compra, precio, id_producto, cantidad) values(" + idCom + ", " + precio + ", " + Prod.getId() + ", " + cantidad + ")";
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);

        } finally {
            Desconectar();
        }
    }

    public void GrabarUsuario(Usuario aux) throws Exception {
        try {
            Conectar();
            String sql = "insert into usuarios (id, nombre, apellido, pass, estado, direccion, telefono, documento, nivel) values ('" + aux.getId() + "', '" + aux.getNombre() + "', '" + aux.getApellido() + "', '" + aux.getPass() + "', '" + aux.getEstado() + "', '" + aux.getDireccion() + "', '" + aux.getTelefono() + "', '" + aux.getDocumento() + "', '" + aux.getNivel() + "') ";

            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);
        } finally {
            Desconectar();
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

    /* public Hashtable TraerDetallesCliente(int id) throws Exception {
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
     int estado= rows.getInt("estado");
     DetalleCompra aux = new DetalleCompra(comp, prec, idprod, cant);
     aux.setIdDetalle(det);

     tabla.put(aux.getIdProd(), aux);
     }
     return tabla;
     } finally {
     Desconectar();
     }
     }*/
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
   
    /*public Hashtable TraerDetallesPendientes() throws Exception {
        try {
            Conectar();
            Hashtable tabla = new Hashtable();
            String sql = "SELECT * FROM detallecompra  d inner join compra c on d.id_compra = c.id where c.estado = 1";
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

    }*/

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

    /*public int TraerIdCompra() throws Exception {
     try {
     Conectar();
     int id = 0;
     String sql = "select max(id) from compra";
     PreparedStatement sent = CrearSentencia(sql);
     ResultSet rows = Consultar(sent);
     if (rows.next()) {
     id = rows.getInt("max(id)");
     }
     return id;
     } finally {
     Desconectar();
     }
    
     }*/
    public int TraerIdUsuario() throws Exception {
        try {
            Conectar();
            int id = 0;
            String sql = "select max(id) from usuarios";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                id = rows.getInt("max(id)");
            }
            return id;

        } finally {
            Desconectar();
        }
    }



    public Usuario TraerUsuario(int id) throws Exception {
        try {
            Conectar();
            Usuario aux = new Usuario();
            String sql = "select * from usuarios where id = '" + id + "'";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                int id_ = (rows.getInt("id"));
                String apellido = (rows.getString("apellido"));
                String nombre = (rows.getString("nombre"));
                String pass = (rows.getString("pass"));
                String direcc = (rows.getString("direccion"));
                int tel = (rows.getInt("telefono"));
                int nivel = (rows.getInt("nivel"));
                int doc = (rows.getInt("documento"));
                int est = (rows.getInt("estado"));
                aux = new Usuario(id_, nombre, apellido, doc, pass, tel, direcc);

                aux.setNivel(nivel);
                aux.setEstado(est);
            }
            return aux;
        } finally {
            Desconectar();
        }
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


    


    /* public Hashtable TraerDetallesCliente(int id) throws Exception {
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
     int estado= rows.getInt("estado");
     DetalleCompra aux = new DetalleCompra(comp, prec, idprod, cant);
     aux.setIdDetalle(det);

     tabla.put(aux.getIdProd(), aux);
     }
     return tabla;
     } finally {
     Desconectar();
     }
     }*/
   
    /*public Hashtable TraerDetallesPendientes() throws Exception {
        try {
            Conectar();
            Hashtable tabla = new Hashtable();
            String sql = "SELECT * FROM detallecompra  d inner join compra c on d.id_compra = c.id where c.estado = 1";
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

    }*/


    /*public int TraerIdCompra() throws Exception {
     try {
     Conectar();
     int id = 0;
     String sql = "select max(id) from compra";
     PreparedStatement sent = CrearSentencia(sql);
     ResultSet rows = Consultar(sent);
     if (rows.next()) {
     id = rows.getInt("max(id)");
     }
     return id;
     } finally {
     Desconectar();
     }
    
     }*/
 

}
