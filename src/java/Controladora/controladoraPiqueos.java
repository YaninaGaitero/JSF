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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class controladoraPiqueos {

    private DaoPiqueo ConexionPiqueos;
    private DaoFacturas ConexionCompras;
    private ArrayList selectedCompras = new ArrayList();

    /**
     * Creates a new instance of controladoraPiqueos
     *
     * @throws java.lang.Exception
     */
    public controladoraPiqueos() throws Exception {
        ConexionPiqueos = new DaoPiqueo();
        ConexionCompras = new DaoFacturas();
    }

    public ArrayList<Compra> listaComprasPend() {
        try {
            ArrayList<Compra> compras = new ArrayList();
            Enumeration elist = ConexionCompras.TraerCompras().elements();
            while (elist.hasMoreElements()) {
                Compra pend = (Compra) elist.nextElement();
                if (pend.getEstado() == 1) {
                    compras.add(pend);
                }
            }
            return compras;
        } catch (Exception ex) {
            Logger.getLogger(controladoraPiqueos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Hashtable detallesAPiquear()
    {
        Hashtable detalles=new Hashtable();
        Iterator idfacts = selectedCompras.iterator();
        while (idfacts.hasNext())
        {
            try 
            {
                String factura = idfacts.next().toString();
                int fact = Integer.parseInt(factura);
                Hashtable detfac = ConexionCompras.TraerDetallesCliente(fact);
                Enumeration elist = detfac.elements();
                while (elist.hasMoreElements())
                {
                    DetalleCompra dc = (DetalleCompra)elist.nextElement();
                    detalles.put(dc.getIdDetalle(), dc);
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(controladoraPiqueos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        selectedCompras.clear();
        return detalles;
    }
    
    public String agregarPiqueos()
    {
        try 
        {
            String answer="piqueos";
            Hashtable detallespiqueos = detallesAPiquear();
            int cabecera = ConexionPiqueos.agregarCompraTopiqueo(detallespiqueos, -1);
            ConexionPiqueos.cambiarEstadoApreparado(cabecera);
            return answer;
        } catch (Exception ex) 
        {
            Logger.getLogger(controladoraPiqueos.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }

    public ArrayList getSelectedCompras() {
        return selectedCompras;
    }

    public void setSelectedCompras(ArrayList selectedCompras) {
        this.selectedCompras = selectedCompras;
    }

}
