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
public class controladoraPiqueos 
{

    private DaoPiqueo ConexionPiqueos;
    
    
    /**
     * Creates a new instance of controladoraPiqueos
     * @throws java.lang.Exception
     */
    public controladoraPiqueos() throws Exception 
    {
        ConexionPiqueos = new DaoPiqueo();
    }
    
    
}
