/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAOs;
import Modelo.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author usuario
 */
public class DAOUsuarios 
extends BBDD
{
    private ArrayList ListaUsers;
    PreparedStatement Insert, Login, Delete, Update, List;
    
    public DAOUsuarios () throws Exception
    {
        super();
        
        
    }
    
    /*public Usuario LoginSistema (String user,String pass) throws Exception
    {
    Usuario entrada=null;
    StringBuffer sentencia;
    sentencia = new StringBuffer();
    sentencia.append("select * from usuarios where ");
    sentencia.append(" username=").append("'").append(user).append("'");
    sentencia.append(" AND ").append(" passkey=").append("'").append(pass).append("'");
    String sql=sentencia.toString();
    Login = getConexion().prepareStatement("select * from usuarios where "+
            "username= ? and passkey= ?");
    Login.setString(1, user);
    Login.setString(2, pass);
    ResultSet rs = Login.executeQuery();
    while (rs.next())
    {
        String userword = rs.getString("username");
        String password = rs.getString("passkey");
        if (userword.equals(user) && password.equals(pass))
        {
            entrada = new Usuario(rs.getInt("id_usuario"),
                    userword,
                    password,
                    rs.getString("nombre"),
                    rs.getString("permiso"),
                    rs.getBoolean("deAlta"),
                    rs.getString("email"));
        }
    }
    Login.close();
    return entrada;
}*/
    
    //private void RefreshUsuarios ()
    public ArrayList ListaUsuarios ()
    {
        //Hashtable Usuarios=new Hashtable();
        
        try 
        {
            ListaUsers=new ArrayList();
            /*String sentencia;
            sentencia="select * from usuarios";
            PreparedStatement ps=crearSentencia(sentencia);
            System.out.println(sentencia);*/
            String sentencia = "select U.id_usuario as id_usuario, "
                              + "U.username as username, "
                              + "U.passkey as passkey, "
                              + "U.nombre as nombre, "
                              + "U.direccion as direccion, "
                              + "C.ciudad as ciudad, "
                              + "U.permiso as permiso, "
                              + "U.deAlta as deAlta, "
                              + "U.email as email "
                              + "from usuarios as U "
                              + "join ciudades as C "
                              + "on U.ciudad_pk=C.id_ciudad ";
            List = getConexion().prepareStatement(sentencia);

            ResultSet rs=List.executeQuery();
            while (rs.next())
            {
                Usuario add=new Usuario(rs.getInt("id_usuario"),
                                      rs.getString("username"),
                                      rs.getString("passkey"),
                                      rs.getString("nombre"),
                                      rs.getString("direccion"),
                                      rs.getString("ciudad"),
                                      rs.getString("permiso"),
                                      rs.getBoolean("deAlta"),
                                      rs.getString("email"));
                ListaUsers.add(add);
            }
            List.close();
            return ListaUsers;
        }
        catch (SQLException E)
        {
            System.out.println(E.getMessage());
            //return Usuarios;
            return ListaUsers;
        }
        
    }
    
    
    public void AgregarUsuario(Usuario add)
    {
        try 
        {
            Insert = getConexion().prepareStatement("insert into usuarios "+
                                                "(username,passkey,nombre,permiso,email) " +
                                                "values ( ? , ? , ? , ? , ? )");
            /*String sentencia;
            sentencia="insert into usuarios " +
            "(username,passkey,nombre,permiso,email)"+
            "values('"+add.getUser()+"','"
            +add.getPass()+"','"
            +add.getNombreCompleto()+"','"
            +add.getPermiso()+"','"
            +add.getEmail()+"')";*/
            Insert.setString(1, add.getUser());
            Insert.setString(2, add.getPass());
            Insert.setString(3, add.getNombreCompleto());
            Insert.setString(4, add.getPermiso());
            Insert.setString(5, add.getEmail());
            Insert.executeUpdate();
            Insert.close();
        
        }
        catch (SQLException E)
        {
            System.out.println(E.getMessage());
        }   

    }
    
    public void DarDeBaja (Usuario ban)
    {
        try 
        {
            String sentencia;
            sentencia="update Usuarios "+
                      "set deAlta=false "+
                      "where id_usuario="+ban.getId_usuario();
            PreparedStatement ps = crearSentencia(sentencia);
            actualizar(ps);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void ModificarUsuario (Usuario mod) 
    {
        try
        {
            String sentencia;
            sentencia="update Usuarios "+
                      "set nombre="+"'"+mod.getNombreCompleto()+"',"+
                      "email='"+mod.getEmail()+"',"+
                      "deAlta="+mod.isDeAlta()+" "+
                      "where id_usuario="+mod.getId_usuario();
            PreparedStatement ps = crearSentencia(sentencia);
            actualizar(ps);
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage()+" ModificarUsuario()");
        }
    }
}
