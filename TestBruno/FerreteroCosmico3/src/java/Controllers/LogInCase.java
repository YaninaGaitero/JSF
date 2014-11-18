/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;
import DAOs.*;
import Modelo.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class LogInCase 
{
    DAOUsuarios ConexionUsuarios;
    private Usuario Logged;
    private String User;
    private String Pass;
    private String Error="";

    
    
    public LogInCase() throws Exception
    {
        ConexionUsuarios = new DAOUsuarios();
    }
    
    
    public boolean getLogueado ()
    {
        boolean alfa=Logged!=null;
        return alfa;
    }
    
    public boolean getComun ()
    {
        boolean alfa = Logged.getPermiso().equals("Comun");
        return alfa;
    }

    public boolean getAdmin ()
    {
        boolean alfa = Logged.getPermiso().equals("Administrador");
        return alfa;
    }
    
    public String login ()
    {
        try {
            ConexionUsuarios.conectar();
            Usuario Log = null;
            ArrayList users = ConexionUsuarios.ListaUsuarios();
            Iterator listausers = users.iterator();
            while (listausers.hasNext())
            {
                Usuario list = (Usuario)listausers.next();
                if (list.getUser().equals(getUser()) && list.getPass().equals(getPass()))
                    Log=list;
            }
            String answer;
            if (Log==null)
            {
                answer="failedlogin";
                Error="Usuario y/o contraseña incorrectos";
            }
            else
            {
                setLogged(Log);
                answer ="success";
                Error="";
            }
            return answer;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(LogInCase.class.getName()).log(Level.SEVERE, null, ex);
            return "failedlogin";
        }
        finally
        {
            ConexionUsuarios.desconectar();
        }
    }
    
    public String logOut ()
    {
        Logged=null;
        Error="";
        
        return "logout";
    }
    
    
    /**
     * @return the Logged
     */
    public Usuario getLogged() {
        return Logged;
    }

    /**
     * @param Logged the Logged to set
     */
    public void setLogged(Usuario Logged) {
        this.Logged = Logged;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }
}
