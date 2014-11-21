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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class ControladoraHistCompraJSF 
{

    DaoFacturas ConexionFacturas;
    
    /**
     * Creates a new instance of ControladoraHistCompraJSF
     * @throws java.lang.Exception
     */
    public ControladoraHistCompraJSF() throws Exception 
    {
        ConexionFacturas = new DaoFacturas();
    }
    
    public ArrayList CabecerasFact(Usuario cliente)
    {
        try 
        {
            ArrayList listafact = new ArrayList();
            Enumeration elist = ConexionFacturas.TraerComprasCliente(cliente.getId()).elements();
            while (elist.hasMoreElements())
            {
                Compra fact = (Compra)elist.nextElement();
                listafact.add(fact);
            }
            return listafact;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
}
