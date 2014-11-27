/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import BD.*;
import Beans.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario
 */
@ManagedBean
@RequestScoped
public class abmUsuariosJSF 
{
    
    DatosUsuario ConexionUsuarios;
    Usuario Adding;
    Usuario Modify;
    int selectUserID;
    
    /**
     * Creates a new instance of abmUsuariosJSF
     */
    public abmUsuariosJSF() throws Exception 
    {
        ConexionUsuarios = new DatosUsuario();
    }
    
    public ArrayList listaUsuarios()
    {
        try 
        {
            ArrayList lista = new ArrayList();
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements())
            {
                Usuario add = (Usuario)elist.nextElement();
                lista.add(add);
            }
            return lista;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
