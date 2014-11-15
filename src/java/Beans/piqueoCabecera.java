/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import BD.DaoPiqueo;
import java.sql.Date;

/**
 *
 * @author Yanina
 */
public class piqueoCabecera {
    private int idPiqueo;
    private Date fecha;
    private int estado;
    private DaoPiqueo bd;
    
    public piqueoCabecera()
    {}
    
    public piqueoCabecera(int idPIqueo, Date fecha, int estado){
        this.idPiqueo=idPIqueo;
        this.fecha=fecha;
        this.estado=estado;
    }

    public int getIdPiqueo() {
        return idPiqueo;
    }

    public void setIdPiqueo(int idPiqueo) {
        this.idPiqueo = idPiqueo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public DaoPiqueo getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DaoPiqueo();
        }
        return bd;
    }

}
