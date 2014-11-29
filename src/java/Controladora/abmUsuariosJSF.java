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
public class abmUsuariosJSF {

    DatosUsuario ConexionUsuarios;
    private Usuario Adding = new Usuario();
    private Usuario Modify = new Usuario();
    private int selectUserID;

    /**
     * Creates a new instance of abmUsuariosJSF
     */
    public abmUsuariosJSF() throws Exception {
        ConexionUsuarios = new DatosUsuario();
    }

    public ArrayList listaUsuarios() {
        try {
            ArrayList lista = new ArrayList();
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements()) {
                Usuario add = (Usuario) elist.nextElement();
                lista.add(add);
            }
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList listaModificar(Usuario admin) {
        try {
            ArrayList listamod = new ArrayList();
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements()) {
                Usuario modi = (Usuario) elist.nextElement();
                if (modi.getNivel() == 1 || modi.getId() == admin.getId()) {
                    listamod.add(modi);
                }
            }
            return listamod;
        } catch (Exception ex) {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String agregarUsuario() {
        try {
            Adding.setEstado(1);
            ConexionUsuarios.GrabarUsuario(Adding);
            return "abmusers";
        } catch (Exception ex) {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }

    public void selectUser() {
        try {
            Usuario modif = null;
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements()) {
                Usuario m = (Usuario) elist.nextElement();
                if (m.getId() == getSelectUserID()) {
                    modif = m;
                }
            }
            setModify(modif);
        } catch (Exception ex) {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String modificarUsuario() {
        try {
            Usuario modif = null;
            Enumeration elist = ConexionUsuarios.TrearUsuariosT().elements();
            while (elist.hasMoreElements()) {
                Usuario m = (Usuario) elist.nextElement();
                if (m.getId() == getSelectUserID()) {
                    modif = m;
                }
            }
            modif.setApellido(Modify.getApellido());
            modif.setDireccion(Modify.getDireccion());
            modif.setDocumento(Modify.getDocumento());
            modif.setNombre(Modify.getNombre());
            modif.setEstado(Modify.getEstado());
            modif.setTelefono(Modify.getTelefono());
            ConexionUsuarios.ModificarUsuario(modif);
            return "abmusers";
        } catch (Exception ex) {
            Logger.getLogger(abmUsuariosJSF.class.getName()).log(Level.SEVERE, null, ex);
            return "failed";
        }
    }

    public Usuario getAdding() {
        return Adding;
    }

    public void setAdding(Usuario Adding) {
        this.Adding = Adding;
    }

    public Usuario getModify() {
        return Modify;
    }

    public void setModify(Usuario Modify) {
        this.Modify = Modify;
    }

    public int getSelectUserID() {
        return selectUserID;
    }

    public void setSelectUserID(int selectUserID) {
        this.selectUserID = selectUserID;
    }
}

