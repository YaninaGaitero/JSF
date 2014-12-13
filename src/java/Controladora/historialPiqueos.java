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
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class historialPiqueos 
{
    
    DaoPiqueo ConexionPiqueos;
    
    private int selectPiqueo=0;
    private piqueoCabecera choosePiqueo=null;
    /**
     * Creates a new instance of historialPiqueos
     * @throws java.lang.Exception
     */
    public historialPiqueos() throws Exception 
    {
        ConexionPiqueos = new DaoPiqueo();
    }
    
    public void elegirPiqueo(AjaxBehaviorEvent E)
    {
        try 
        {
            choosePiqueo = ConexionPiqueos.getbyIDcabPiqueo(selectPiqueo);
        } 
        catch (Exception ex) {
            Logger.getLogger(historialPiqueos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<piqueoCabecera> listaPiqueos()
    {
        try 
        {
            ArrayList<piqueoCabecera> listaPiqueos = new ArrayList<piqueoCabecera>();
            Enumeration elist = ConexionPiqueos.getCabecerasPiqueo().elements();
            while (elist.hasMoreElements())
            {
                piqueoCabecera pc = (piqueoCabecera)elist.nextElement();
                listaPiqueos.add(pc);
            }
            return listaPiqueos;
        } catch (Exception ex) 
        {
            Logger.getLogger(historialPiqueos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Piqueo> detallesPiqueos()
    {
        try {
            ArrayList<Piqueo> Detalles = new ArrayList<Piqueo>();
            Enumeration elist = ConexionPiqueos.traerPiqueoDetalle(selectPiqueo).elements();
            while (elist.hasMoreElements())
            {
                Piqueo pq = (Piqueo)elist.nextElement();
                Detalles.add(pq);
            }
            return Detalles;
        } catch (Exception ex) {
            Logger.getLogger(historialPiqueos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean hayPiqueo()
    {
        boolean alfa=choosePiqueo!=null;
        return alfa;      
    }

    public int getSelectPiqueo() {
        return selectPiqueo;
    }

    public void setSelectPiqueo(int selectPiqueo) {
        this.selectPiqueo = selectPiqueo;
    }

    public piqueoCabecera getChoosePiqueo() {
        return choosePiqueo;
    }

    public void setChoosePiqueo(piqueoCabecera choosePiqueo) {
        this.choosePiqueo = choosePiqueo;
    }
    
}
