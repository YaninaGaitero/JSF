/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class BBDD {
    
    protected Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
     
       
       
    
  
    public BBDD() throws Exception
    {
        
        this.Conectar();
    }
    
    public void Conectar () throws Exception
    {
        try {
             Class.forName("com.mysql.jdbc.Driver");
        }
        catch( ClassNotFoundException ex){ 
            System.out.print("Error de driver " + ex.getMessage());           
        }
        try
        {
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab","root" ,"");
           
        }
        catch(SQLException ex)
        {
            System.out.append("Error de conection " + ex.getMessage());
        }
    }
    
    public PreparedStatement CrearSentencia(String sql) throws Exception
    {
        try
        {
            PreparedStatement e = getConexion().prepareStatement(sql);
          return e;
        }
        catch (SQLException e)
        {
              throw new SQLException ("Error de sentencia "+e.getMessage());
        }
          
    }
   
    
    public ResultSet Consultar (PreparedStatement sentencia) throws Exception
    {
        try
        {
           ResultSet resultado1 = sentencia.executeQuery();
           return resultado1;
        }
        catch (SQLException e)
        {
            throw new SQLException ("Error de consulta "+e.getMessage());
        }
    }
    
    public void Desconectar ()
    {
        try
        {
            getConexion().close();
         } catch (SQLException e)
        {
            setConexion(null);
        }
    }
    
    public void Actualizar (PreparedStatement sentencia) throws Exception
    {
        try
        {
            sentencia.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException("Error de actualizacion "+e.getMessage());
        }
    }
    
    public void insertar(PreparedStatement sentencia) throws SQLException
{
        sentencia.execute();
}
    
    /**
     * @return the driver


    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * @param conexion the conexion to set
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * @return the sentencia
     */
    public Statement getSentencia() {
        return sentencia;
    }

    /**
     * @param sentencia the sentencia to set
     */
    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }

    /**
     * @return the resultado
     */
    public ResultSet getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(ResultSet resultado) {
        this.resultado = resultado;
    }   
    
}
