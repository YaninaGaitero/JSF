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
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author usuario
 */
@ManagedBean
@SessionScoped
public class ControladoraHistCompraJSF 
{

    DaoFacturas ConexionFacturas;
    private int IDCompra=0;
    private Compra selCompra=null;
    /**
     * Creates a new instance of ControladoraHistCompraJSF
     * @throws java.lang.Exception
     */
    public ControladoraHistCompraJSF() throws Exception 
    {
        ConexionFacturas = new DaoFacturas();
    }
    
    public ArrayList cabecerasFact(Usuario cliente)
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
    
    
    
    public double total()
    {
        try 
        {
            double result = ConexionFacturas.getTotal(IDCompra);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ArrayList detallesFact (int id_client)
    {
        try 
        {
            ArrayList detalles = new ArrayList();
            Enumeration elist = ConexionFacturas.TraerDetallesCliente(id_client).elements();
            while (elist.hasMoreElements())
            {
                DetalleCompra add = (DetalleCompra)elist.nextElement();
                if (add.getIdCompra()==getIDCompra())
                    detalles.add(add);
            }
            return detalles;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void selFactura()
    {
        try 
        {
            Compra ret=null;
            Enumeration elist = ConexionFacturas.TraerCompras().elements();
            while (elist.hasMoreElements())
            {
                Compra c = (Compra)elist.nextElement();
                if(c.getIdCompra()==getIDCompra())
                {
                    ret = c;
                }
            }
            setSelCompra(ret);

        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList detallesUltimos(int idComp)
    {
        try 
        {
            ArrayList detalles = new ArrayList();
            Enumeration elist = ConexionFacturas.TraerDetallesCliente(idComp).elements();
            while (elist.hasMoreElements())
            {
                DetalleCompra add = (DetalleCompra)elist.nextElement();
                if (add.getIdCompra()==idComp)
                    detalles.add(add);
            }
            return detalles;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Compra ultimaCompra(int ultima)
    {
        try 
        {
            Compra realizada = ConexionFacturas.traerCompraBYid(ultima);
            return realizada;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public double ultimoTotal(int ultimo)
    {
        try 
        {
            double result = ConexionFacturas.getTotal(ultimo);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    

    public int getIDCompra() {
        return IDCompra;
    }

    public void setIDCompra(int IDCompra) {
        this.IDCompra = IDCompra;
    }

    public Compra getSelCompra() {
        return selCompra;
    }

    public void setSelCompra(Compra selCompra) {
        this.selCompra = selCompra;
    }
   public void selectEvent(AjaxBehaviorEvent E)throws Exception
    {
            Enumeration listu = ConexionFacturas.TraerDetallesCliente(IDCompra).elements();
            while (listu.hasMoreElements())
            {
                Compra p = (Compra)listu.nextElement();
                if (p.getIdCompra()==IDCompra)
                    setSelCompra(p);
            }
    }
    
    
}
