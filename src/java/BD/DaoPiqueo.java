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

/**
 *
 * @author Yanina
 */
public class DaoPiqueo extends BBDD{
    
    public DaoPiqueo()throws Exception{
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
            String sql = "select * from piqueo where idPiqueo = " + idPiqueo + " and idProducto ="+ idProducto;
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
    
   private piqueoCabecera getbyIDcabPiqueo(int idPiqueo) throws Exception {
        try {
            Conectar();
            piqueoCabecera piqueoCabecera = null;
            String sql = "select * from piqueoCabecera where idPiqueo = " + idPiqueo ;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
               int estado=rows.getInt("estado");
               Date fecha=rows.getDate("fecha");
               piqueoCabecera = new piqueoCabecera(idPiqueo, fecha,estado);
            }
            return piqueoCabecera;
        } finally {
            Desconectar();
        }
    }
   
   public Hashtable getCabecerasPiqueoByEstado(int estado)throws Exception{
         try {
            Conectar();
            piqueoCabecera piqueoCabecera = null;
            String sql = "select * from piqueoCabecera where estado = " + estado ;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            Hashtable listaCab= new  Hashtable();
            while (rows.next()) {
               int idPiqueo=rows.getInt("idPiqueo");
               Date fecha=rows.getDate("fecha");
               piqueoCabecera = new piqueoCabecera(idPiqueo, fecha,estado);
               listaCab.put(idPiqueo, piqueoCabecera);
            }
            return listaCab;
        } finally {
            Desconectar();
        }  
   }
      public Hashtable traerPiqueoDetalle(int idPiqueo) throws Exception {
        Hashtable listaPiqueo= new  Hashtable();
          try {
            Conectar();
            Piqueo piqueo = null;
            String sql = "select * from piqueo where idPiqueo = " + idPiqueo ;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            while (rows.next()) {
                String descripcionProducto = rows.getString("descripcion");
                int cantidad = rows.getInt("cantidad");
                int idProducto= rows.getInt("idProducto");
                piqueo = new Piqueo(idPiqueo, descripcionProducto, cantidad ,idProducto);
                listaPiqueo.put(idProducto, piqueo);
            }

            return listaPiqueo;
        } finally {
            Desconectar();
        }
    }
      
      
      public Hashtable traerPiqueosPendientes() throws Exception {
        Hashtable listaPiqueo= new  Hashtable();
          try {
            Conectar();
            piqueoCabecera piqueo = null;
            String sql = "select * from piqueocabecera where estado = " + 1 ;
            PreparedStatement sent = CrearSentencia(sql);
            ResultSet rows = Consultar(sent);
            int i=0;
            while (rows.next()) {
                int estado = rows.getInt("ESTADO");
                Date fecha= rows.getDate("fecha");
                int idPiqueo= rows.getInt("idPiqueo");
                piqueo = new piqueoCabecera(idPiqueo, fecha, estado);
                listaPiqueo.put(i, piqueo);
                i++;
            }

            return listaPiqueo;
        } finally {
            Desconectar();
        }
    }

   
    
    public int agregarCompraTopiqueo(Hashtable detalle, int grabaCab) throws  Exception {
        
        /// recibe por parametro grabaCab -1 si es la primera vez
// el metodo devuelve -1 si se ejecuto correctamente y (el idCompra si la misma no pudo ser grabada en 3ra posicion 1 si se grabo cabeceray 0 si la cabecera ya estaba grabada)
        //2 posicion-1 si se ejecuto correctamente 
        //              idCompra si hubo algun error /
        //3posicion 1 si se grabo cabecera
                    //0 si no se grabo cabecera
        // 1 posicion idPiqueo
	PreparedStatement preparedStatementModificaStock = null;        
	PreparedStatement preparedStatementInsertaEnPqueo = null;
        PreparedStatement preparedStatementActualizarCompra=null;
        PreparedStatement preparedStatementUpdatePiqueo=null;   
        PreparedStatement preparedStatementInsertaCabecera=null;

        String insertaCabecera="INSERT INTO PIQUEOCABECERA VALUES (?,?,?)";
	String updatecompra = "UPDATE compra SET estado=? WHERE id=?";
	String updateTableProductos = "UPDATE productos SET stock= stock - ? WHERE id= ?";
        String insertarTablePiqueo= "INSERT INTO piqueo(idPiqueo, cantidad, descripcion, idProducto) VALUES (?,?,?,?)";
        String updatePiqueo="UPDATE piqueo SET cantidad= cantidad + ? WHERE idProducto=? and idPiqueo = ?";
        
        int idCompra=0;
        DetalleCompra auxdet=null;
        String prodDescripcion;
        DatosUsuario dao=new DatosUsuario();
        DatosProductos daoProd= new DatosProductos();
        int idPiqueo=0;
        int rta=0;
        idPiqueo=maxIdPiqueo()+1;      
        
	try {
            Conectar();
            conexion.setAutoCommit(false);
            if(grabaCab==-1){

                preparedStatementInsertaCabecera= conexion.prepareStatement(insertaCabecera);
                preparedStatementInsertaCabecera.setInt(1, idPiqueo);
                preparedStatementInsertaCabecera.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
                preparedStatementInsertaCabecera.setInt(3,1);
                preparedStatementInsertaCabecera.executeUpdate();
                rta= idPiqueo;
            }
            if(rta==0){;
                rta=grabaCab;
            }
            Enumeration i = detalle.elements();
            while (i.hasMoreElements()) {// MODIFICA EL STOCK 
                auxdet = new DetalleCompra();
                auxdet = (DetalleCompra) i.nextElement();
                preparedStatementModificaStock = conexion.prepareStatement(updateTableProductos);
                preparedStatementModificaStock.setInt(1,auxdet.getCantidad());
                preparedStatementModificaStock.setInt(2, auxdet.getIdProd());
                preparedStatementModificaStock.executeUpdate();
                
                prodDescripcion= daoProd.TraerNombreProducto1(auxdet.getIdProd());
                Piqueo p = getbyIDLineaPiqueo(idPiqueo,auxdet.getIdProd());// verifica si existe una linea de piqueo con mismo cod de prod e idpPROD
                if(p==null){
                //GRABO PIQUEO YA Q NO EXISTE
                    preparedStatementInsertaEnPqueo= conexion.prepareStatement(insertarTablePiqueo);
                    preparedStatementInsertaEnPqueo.setInt(1,idPiqueo);
                    preparedStatementInsertaEnPqueo.setInt(2, auxdet.getCantidad());
                    preparedStatementInsertaEnPqueo.setString(3, prodDescripcion);
                    preparedStatementInsertaEnPqueo.setInt(4, auxdet.getIdProd());
                    preparedStatementInsertaEnPqueo.executeUpdate();
                
                }else{
                    preparedStatementUpdatePiqueo= conexion.prepareStatement(updatePiqueo);
                    preparedStatementUpdatePiqueo.setInt(1, auxdet.getCantidad());
                    preparedStatementUpdatePiqueo.setInt(2, auxdet.getIdProd());
                    preparedStatementUpdatePiqueo.setInt(3, idPiqueo);
                    preparedStatementUpdatePiqueo.executeUpdate();
                }
            }
            if(auxdet!=null){
                idCompra=auxdet.getIdCompra();
            }
            
            //modificar estado de la compra
            preparedStatementActualizarCompra = conexion.prepareStatement(updatecompra);
            preparedStatementActualizarCompra.setInt(1,2 );
            preparedStatementActualizarCompra.setInt(2, idCompra);
	    preparedStatementActualizarCompra.executeUpdate();         
            conexion.commit();
	} catch (Exception e) {
		conexion.rollback();
                rta=-1;
                return rta;
	} finally {
 		if (preparedStatementModificaStock != null) {
			preparedStatementModificaStock.close();
		}
		if (preparedStatementInsertaEnPqueo != null) {
			preparedStatementInsertaEnPqueo.close();
		}
                if(preparedStatementActualizarCompra!=null){
                    preparedStatementActualizarCompra.close();
                }
                if(preparedStatementUpdatePiqueo!=null){
                    preparedStatementUpdatePiqueo.close();
                }
                if(preparedStatementInsertaCabecera!=null){
                    preparedStatementInsertaCabecera.close();
                }
		if (conexion != null) {
			conexion.close();
		}

 
	} 
        return rta;
    }
    
    public void cambiarEstadoApreparado(int idPiqueo) throws Exception{
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
