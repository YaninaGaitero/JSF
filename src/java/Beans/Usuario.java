package Beans;

import BD.DatosUsuario;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Usuario {

    /**
     * @return the cuenta
     */
   
    
    private DatosUsuario bd;
    private String nombre;
    private int id;
    private String apellido;
    private int documento;
    private int estado;
    private String pass;
    private int nivel;
    private int telefono;
    private String direccion;
    private boolean logueado = false;
   
    public Usuario (){}
    
    public Usuario (int Id, String nom, String apell, int doc,  String pw, int tel, String direc)
    {
        this.nombre = nom;
        this.id = Id;
        this.apellido = apell;
        this.documento = doc;
        this.estado = 1;
        this.pass = pw;
        this.nivel = 2;
        this.telefono = tel;
        this.direccion = direc;
        
    }
    
        public String LoginUsuario() {
        String rta = " No esta logueado";

        try {
            Usuario aux = getBd().Logueo(this.nombre, this.pass);
            if (aux != null) {
                this.setId(aux.getId());
                this.setNombre(aux.getNombre());
                this.setPass(aux.getPass());
                this.setNivel(aux.getNivel());
                this.setLogueado(true);
                rta = "logueado";

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return rta;
    }

    public String logout() {
        // seteo valores por defecto
        this.setId(0);
        this.setNombre(null);
        this.setPass(null);
        this.setNivel(0);
        this.setLogueado(false);
        return "index";
    }

    public DatosUsuario getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DatosUsuario();
        }
        return bd;
    }
    
    
    public String tipoUsuario()
    {
        if (getNivel()==1)
            return "Administrador";
        else
            return "Comun";
    }
    
    public String activo()    
    {
        if(getEstado()==1)
            return "Activo";
        else
            return "No Activo";
    }
            
            /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the documento
     */
    public int getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(int documento) {
        this.documento = documento;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(DatosUsuario bd) {
        this.bd = bd;
    }

    /**
     * @return the logueado
     */
    public boolean isLogueado() {
        return logueado;
    }

    /**
     * @param logueado the logueado to set
     */
    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
    
}
