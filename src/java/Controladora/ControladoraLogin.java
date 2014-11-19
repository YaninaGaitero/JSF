/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;
import Beans.*;
import BD.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ControladoraLogin 
{
    DatosUsuario ConexionUsuarios;
    
    private Usuario userlog;
    private String nombre;
    private String pass;

    public ControladoraLogin() throws Exception 
    {
        this.ConexionUsuarios = new DatosUsuario();
    }
    
    public String logIn()
    {
        try 
        {
            String answer;
            Hashtable listu = ConexionUsuarios.TrearUsuariosT();
            Enumeration lista = listu.elements();
            while (lista.hasMoreElements())
            {
                Usuario log = (Usuario)lista.nextElement();
                if (log.getNombre().equals(nombre) && log.getPass().equals(pass))
                    userlog = log;
            }
            if (userlog!=null)
            {
                answer = "logueado";
            }
            else
            {
                answer = "failed";
            }
            return answer;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraLogin.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }

    }
    
    public String logOut()
    {
        userlog = null;
        ConexionUsuarios.Desconectar();
        return "logout";
    }
    
    public boolean isLogged()
    {
        boolean alfa = userlog != null;
        return alfa;
    }
    
    public boolean isAdmin()
    {
        if (isLogged())
        {
            boolean alfa = userlog.getNivel()==1;
            return alfa;
        }
        else
            return false;
    }
    
    public boolean isComun()
    {
        if (isLogged())
        {
            boolean alfa = userlog.getNivel()==2;
            return alfa;
        }
        else
            return false;
    }

    public Usuario getUserlog() {
        return userlog;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
