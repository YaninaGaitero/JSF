/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Beans.Compra;
import Beans.DetalleCompra;
import Beans.Piqueo;
import Beans.Producto;
import Beans.piqueoCabecera;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import Beans.StockError;
/**
 *
 * @author Yanina
 */
public class DaoPiqueo extends BBDD {

    public DaoPiqueo() throws Exception {
        super();
    }

    public int maxIdPiqueo() throws Exception {
        try {
            Conectar();
            int id = 0;
            String sql = "select max(idPiqueo) from piqueoCabecera";
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            if (rows.next()) {
                id = rows.getInt("max(idPiqueo)");
            }
            return id;

        } finally {
            Desconectar();
        }
    }

    public void BajarEstado(int id) throws Exception {
        try {
            Conectar();
            String sql = "update piqueocabecera set estado = 2 where id = " + id + "";
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);
        } finally {
            Desconectar();
        }

    }

    private Piqueo getbyIDLineaPiqueo(int idPiqueo, int idProducto) throws Exception {
        try {

            Piqueo piqueo = null;
            String sql = "select * from piqueo where idPiqueo = " + idPiqueo + " and idProducto =" + idProducto;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                String descripcionProducto = rows.getString("descripcion");
                int cantidad = rows.getInt("cantidad");
                piqueo = new Piqueo(idPiqueo, descripcionProducto, cantidad, idProducto);

            }

            return piqueo;
        } finally {
        }
    }

    public piqueoCabecera getbyIDcabPiqueo(int idPiqueo) throws Exception {
        try {
            Conectar();
            piqueoCabecera piqueoCabecera = null;
            String sql = "select * from piqueoCabecera where idPiqueo = " + idPiqueo;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                int estado = rows.getInt("estado");
                Date fecha = rows.getDate("fecha");
                piqueoCabecera = new piqueoCabecera(idPiqueo, fecha, estado);
            }
            return piqueoCabecera;
        } finally {
            Desconectar();
        }
    }

    public Hashtable getCabecerasPiqueoByEstado(int estado) throws Exception {
        try {
            Conectar();
            piqueoCabecera piqueoCabecera;
            String sql = "select * from piqueoCabecera where estado = " + estado;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            Hashtable listaCab = new Hashtable();
            while (rows.next()) {
                int idPiqueo = rows.getInt("idPiqueo");
                Date fecha = rows.getDate("fecha");
                piqueoCabecera = new piqueoCabecera(idPiqueo, fecha, estado);
                listaCab.put(idPiqueo, piqueoCabecera);
            }
            return listaCab;
        } finally {
            Desconectar();
        }
    }

    public Hashtable traerPiqueoDetalle(int idPiqueo) throws Exception {
        Hashtable listaPiqueo = new Hashtable();
        try {
            Conectar();
            Piqueo piqueo;
            String sql = "select * from piqueo where idPiqueo = " + idPiqueo;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                String descripcionProducto = rows.getString("descripcion");
                int cantidad = rows.getInt("cantidad");
                int idProducto = rows.getInt("idProducto");
                piqueo = new Piqueo(idPiqueo, descripcionProducto, cantidad, idProducto);
                listaPiqueo.put(idProducto, piqueo);
            }

            return listaPiqueo;
        } finally {
            Desconectar();
        }
    }

    public Hashtable traerPiqueosPendientes() throws Exception {
        Hashtable listaPiqueo = new Hashtable();
        try {
            Conectar();
            piqueoCabecera piqueo;
            String sql = "select * from piqueocabecera where estado = " + 1;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            int i = 0;
            while (rows.next()) {
                int estado = rows.getInt("ESTADO");
                Date fecha = rows.getDate("fecha");
                int idPiqueo = rows.getInt("idPiqueo");
                piqueo = new piqueoCabecera(idPiqueo, fecha, estado);
                listaPiqueo.put(i, piqueo);
                i++;
            }

            return listaPiqueo;
        } finally {
            Desconectar();
        }
    }

    public int agregarCompraTopiqueo(Hashtable detalle, int grabaCab, StockError error) throws Exception {

        /// recibe por parametro grabaCab -1 si es la primera vez
        // el metodo devuelve -1 si se ejecuto correctamente y (el idCompra si la misma no pudo ser grabada en 3ra posicion 1 si se grabo cabeceray 0 si la cabecera ya estaba grabada)
        //2 posicion-1 si se ejecuto correctamente 
        //              idCompra si hubo algun error /
        //3posicion 1 si se grabo cabecera
        //0 si no se grabo cabecera
        // 1 posicion idPiqueo
        PreparedStatement psModificaStock = null;
        PreparedStatement psInsertaEnPiqueo = null;
        PreparedStatement psActualizarCompra = null;
        PreparedStatement psUpdatePiqueo = null;
        PreparedStatement psInsertaCabecera = null;

        String insertaCabecera = "INSERT INTO PIQUEOCABECERA VALUES (?,?,?)";
        String updatecompra = "UPDATE compra SET estado=? WHERE id=?";
        String updateTableProductos = "UPDATE productos SET stock= stock - ? WHERE id= ?";
        String insertarTablePiqueo = "INSERT INTO piqueo(idPiqueo, cantidad, descripcion, idProducto) VALUES (?,?,?,?)";
        String updatePiqueo = "UPDATE piqueo SET cantidad= cantidad + ? WHERE idProducto=? and idPiqueo = ?";

        int idCompra = 0;
        
        String prodDescripcion;
        DatosUsuario dao = new DatosUsuario();
        DatosProductos daoProd = new DatosProductos();
        int idPiqueo;
        int rta = 0;
        idPiqueo = maxIdPiqueo() + 1;
        StockError verif=new StockError();

        try {
            Conectar();
            conexion.setAutoCommit(false);
            if (grabaCab == -1) {

                psInsertaCabecera = conexion.prepareStatement(insertaCabecera);
                psInsertaCabecera.setInt(1, idPiqueo);
                psInsertaCabecera.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                psInsertaCabecera.setInt(3, 1);
                psInsertaCabecera.executeUpdate();
                rta = idPiqueo;
            }
            if (rta == 0) {
                rta = grabaCab;
            }
            Enumeration i = detalle.elements();
            while (i.hasMoreElements()) {// MODIFICA EL STOCK 

                DetalleCompra aux = (DetalleCompra) i.nextElement();
                prodDescripcion = daoProd.TraerNombreProducto1(aux.getIdProd());
                verif.setCompra(aux.getIdCompra());
                verif.setProducto(prodDescripcion);
                verif.setStock(aux.getCantidad());
                psModificaStock = conexion.prepareStatement(updateTableProductos);
                psModificaStock.setInt(1, aux.getCantidad());
                psModificaStock.setInt(2, aux.getIdProd());
                psModificaStock.executeUpdate();
                psModificaStock.close();

                
                Piqueo p = getbyIDLineaPiqueo(idPiqueo, aux.getIdProd());// verifica si existe una linea de piqueo con mismo cod de prod e idpPROD
                
                if (p == null) {
                    //GRABO PIQUEO YA Q NO EXISTE
                    psInsertaEnPiqueo = conexion.prepareStatement(insertarTablePiqueo);
                    psInsertaEnPiqueo.setInt(1, idPiqueo);
                    psInsertaEnPiqueo.setInt(2, aux.getCantidad());
                    psInsertaEnPiqueo.setString(3, prodDescripcion);
                    psInsertaEnPiqueo.setInt(4, aux.getIdProd());
                    psInsertaEnPiqueo.executeUpdate();
                    psInsertaEnPiqueo.close();

                } else {
                    psUpdatePiqueo = conexion.prepareStatement(updatePiqueo);
                    psUpdatePiqueo.setInt(1, aux.getCantidad());
                    psUpdatePiqueo.setInt(2, aux.getIdProd());
                    psUpdatePiqueo.setInt(3, idPiqueo);
                    psUpdatePiqueo.executeUpdate();
                    psUpdatePiqueo.close();
                }
                

                
            }
            Enumeration facturasAct = detalle.elements();
            while (facturasAct.hasMoreElements())
            {
                DetalleCompra dc = (DetalleCompra)facturasAct.nextElement();
                //modificar estado de la compra
                psActualizarCompra = conexion.prepareStatement(updatecompra);
                psActualizarCompra.setInt(1, 2);
                psActualizarCompra.setInt(2, dc.getIdCompra());
                psActualizarCompra.executeUpdate();
                psActualizarCompra.close();
            }


            
            conexion.commit();
        } catch (Exception e) {
            error.setCompra(verif.getCompra());
            error.setProducto(verif.getProducto());
            error.setStock(verif.getStock());
            System.out.println(e.getMessage());
            conexion.rollback();
            rta = -1;
            return rta;
        } finally {
            if (psModificaStock != null) {
                psModificaStock.close();
            }
            if (psInsertaEnPiqueo != null) {
                psInsertaEnPiqueo.close();
            }
            if (psActualizarCompra != null) {
                psActualizarCompra.close();
            }
            if (psUpdatePiqueo != null) {
                psUpdatePiqueo.close();
            }
            if (psInsertaCabecera != null) {
                psInsertaCabecera.close();
            }
            if (conexion != null) {
                conexion.close();
            }

        }
        return rta;
    }

    public void cambiarEstadoApreparado(int idPiqueo) throws Exception {
        try {
            Conectar();
            String sql = "update piqueoCabecera set estado = 2 where idPiqueo = " + idPiqueo;
            PreparedStatement sent = CrearSentencia(sql);
            Actualizar(sent);
        } finally {
            Desconectar();
        }
    }

}
