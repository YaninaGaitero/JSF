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
public class HistClientes {

    DatosUsuario ConexionUsuarios;
    DaoFacturas ConexionCompras;
    
    private Usuario chooseUser=null;
    private int idUsuario=0;
    private Compra chooseCompra=null;
    private int idCompra=0;
    
    
    /**
     * Creates a new instance of HistClientes
     * @throws java.lang.Exception
     */
    public HistClientes() throws Exception 
    {
        ConexionUsuarios = new DatosUsuario();
        ConexionCompras = new DaoFacturas();
    }
    
    public ArrayList listaUsuarios()
    {
        try 
        {
            ArrayList lista = new ArrayList();
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements())
            {
                Usuario user=(Usuario)elist.nextElement();
                if (user.getNivel()==2)
                    lista.add(user);
            }
            return lista;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(HistClientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void selectUsuario(AjaxBehaviorEvent E)
    {
        try 
        {
            Enumeration listuser = ConexionUsuarios.TrearUsuariosT().elements();
            while (listuser.hasMoreElements())
            {
                Usuario user = (Usuario)listuser.nextElement();
                if (user.getId()==getIdUsuario())
                    setChooseUser(user);
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(HistClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList listaComprasXUsuario()
    {
        try 
        {
            ArrayList compras = new ArrayList();
            Enumeration eList = ConexionCompras.TraerComprasCliente(getChooseUser().getId()).elements();
            while (eList.hasMoreElements())
            {
                Compra com = (Compra)eList.nextElement();
                compras.add(com);
            }
            return compras;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(HistClientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void selectCompra(AjaxBehaviorEvent E)
    {
        try 
        {
            Enumeration listcomp = ConexionCompras.TraerComprasCliente(chooseUser.getId()).elements();
            while (listcomp.hasMoreElements())
            {
                Compra comp = (Compra)listcomp.nextElement();
                if (comp.getIdCompra()==getIdCompra())
                    setChooseCompra(comp);
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(HistClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList listaDetallesCompra ()
    {
        try 
        {
            ArrayList detalles = new ArrayList();
            Enumeration dlist = ConexionCompras.TraerDetallesCliente(idCompra).elements();
            while (dlist.hasMoreElements())
            {
                DetalleCompra dc = (DetalleCompra)dlist.nextElement();
                detalles.add(dc);
            }
            return detalles;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(HistClientes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public double total()
    {
        try 
        {
            double result = ConexionCompras.getTotal(chooseCompra.getIdCompra());
            return result;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraHistCompraJSF.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public boolean hayFactura()
    {
        boolean alfa = false;
        if (idCompra != 0 || idUsuario != 0)
        {
            if (idCompra!=0)
                alfa = chooseCompra.getIdUsuario()==chooseUser.getId();
        }
        return alfa;
    }
    
    public boolean hayComprasRealizadas()
    {
        boolean alfa=false;
        ArrayList verif = listaComprasXUsuario();
        if (verif!=null)
            alfa = !verif.isEmpty();
        return alfa;
    }

    public Usuario getChooseUser() {
        return chooseUser;
    }

    public void setChooseUser(Usuario chooseUser) {
        this.chooseUser = chooseUser;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Compra getChooseCompra() {
        return chooseCompra;
    }

    public void setChooseCompra(Compra chooseCompra) {
        this.chooseCompra = chooseCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    
}
