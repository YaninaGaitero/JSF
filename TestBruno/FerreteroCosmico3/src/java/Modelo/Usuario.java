/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Usuario 
implements Serializable
{
    private int id_usuario;
    private String user;
    private String pass;
    private String nombreCompleto;
    /*
    Permisos:
    "Administrador"
    "Comun"
    */
    private String direccion;
    private String ciudad;
    private String permiso;
    private boolean deAlta;
    private String Email;

    public Usuario(String user, String pass, String nombreCompleto, String permiso, String Email) {
        id_usuario=0;
        deAlta=true;
        this.user = user;
        this.pass = pass;
        this.nombreCompleto = nombreCompleto;
        this.permiso = permiso;
        this.Email = Email;
    }

    public Usuario
        (int id_usuario, String user, String pass, String nombreCompleto, String direccion, String ciudad, String permiso, boolean deAlta, String Email) {
        this.id_usuario = id_usuario;
        this.user = user;
        this.pass = pass;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.permiso = permiso;
        this.deAlta = deAlta;
        this.Email = Email;
    }

    public Usuario() 
    {
    }
    
    
    
    
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPermiso() {
        return permiso;
    }
    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public boolean isDeAlta() {
        return deAlta;
    }
    public void setDeAlta(boolean deAlta) {
        this.deAlta = deAlta;
    }
    
    /*public String HTMLTableData ()
    {
    String html;
    html="<table border='2'>"+
    "<tr><td>ID</td><td>"+ id_usuario +"</td></tr>"+
    "<tr><td>User</td><td>"+ user +"</td></tr>"+
    "<tr><td>Nombre</td><td>"+ nombreCompleto+"</td></tr>"+
    "<tr><td>Permiso</td><td>"+ permiso+ "</td></tr>"+
    "<tr><td>E-mail</td><td>"+ Email+ "</td></tr>"+
    "</table>";
    return html;
    }
    */

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
