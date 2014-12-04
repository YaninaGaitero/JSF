/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import BD.*;
import Beans.*;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class HistClientes {

    DatosUsuario ConexionUsuarios;
    DaoFacturas ConexionCompras;
    
    
    /**
     * Creates a new instance of HistClientes
     * @throws java.lang.Exception
     */
    public HistClientes() throws Exception 
    {
        ConexionUsuarios = new DatosUsuario();
        ConexionCompras = new DaoFacturas();
    }
    
}
