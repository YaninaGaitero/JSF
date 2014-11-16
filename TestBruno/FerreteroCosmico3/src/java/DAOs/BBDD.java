/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ara
 */
public class BBDD
{
    private String driver = "com.mysql.jdbc.Driver";
    private String servidor = "localhost";
    private String url = "jdbc:mysql://";
    private int puerto = 3306;
    private String usuario = "root";
    private String password = "";
    private String BD = "FerreteroCosmico2";
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    public BBDD() throws Exception
    {
        url = url + servidor + ":" + puerto + "/" + BD;
        this.conectar();
    }

    public final void conectar() throws Exception
    {
        try
        {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e)
        {
            throw new Exception("Error de driver" + e.getMessage());
        }
        try
        {
            setConexion(DriverManager.getConnection(getUrl(), getUsuario(), getPassword()));
        } catch (SQLException e)
        {
            throw new Exception("Error en la conexion \n codigo" + e.getErrorCode() + "Explicacion:" + e.getMessage());
        }
    }

    public int actualizar(PreparedStatement sentencia) throws Exception
    {
        try
        {
            int res = sentencia.executeUpdate();
            return res;
        } catch (SQLException e)
        {
            throw new Exception("Error al ejecutar sentencia \n codigo" + e.getErrorCode() + "Explicacion:" + e.getMessage());
        }
    }

    public ResultSet consultar(PreparedStatement sentencia) throws Exception
    {
        try
        {
            ResultSet rows = sentencia.executeQuery();
            return rows;
        } catch (SQLException e)
        {
            throw new SQLException("Error al ejecutar consulta" + e.getMessage());
        }
        
    }

    public final void desconectar()
    {
        try
        {
            getConexion().close();
        } catch (SQLException e)
        {
            setConexion(null);
        }
    }

    public PreparedStatement crearSentencia(String sql) throws Exception
    {
        try
        {
            PreparedStatement s = getConexion().prepareStatement(sql);
            return s;
        } catch (SQLException e)
        {
            throw new SQLException("Error al ejecutar sentencia \n codigo" + e.getErrorCode() + "Explicacion:" + e.getMessage());
        }
    }


    public String getDriver()
    {
        return driver;
    }
    public void setDriver(String driver)
    {
        this.driver = driver;
    }


    public String getServidor()
    {
        return servidor;
    }
    public void setServidor(String servidor)
    {
        this.servidor = servidor;
    }


    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }


    public int getPuerto()
    {
        return puerto;
    }
    public void setPuerto(int puerto)
    {
        this.puerto = puerto;
    }


    public String getUsuario()
    {
        return usuario;
    }
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }


    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getBD()
    {
        return BD;
    }
    public void setBD(String BD)
    {
        this.BD = BD;
    }


    public Connection getConexion()
    {
        return conexion;
    }
    public void setConexion(Connection conexion)
    {
        this.conexion = conexion;
    }


    /*public PreparedStatement getSentencia()
    {
    return sentencia;
    }
    public void setSentencia(PreparedStatement sentencia)
    {
    this.sentencia = sentencia;
    }
    
    
    public ResultSet getResultado()
    {
    return resultado;
    }
    public void setResultado(ResultSet resultado)
    {
    this.resultado = resultado;
    }*/
}
