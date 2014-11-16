/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import DAOs.*;
import Modelo.*;
import java.util.ArrayList;
/**
 *
 * @author usuario
 */
public class ListUsersCase 
{
    DAOUsuarios ConexionUsuarios;
    private ArrayList Lista;
    
    public ListUsersCase () throws Exception
    {
        ConexionUsuarios = new DAOUsuarios();
    }
    
    private void Usuarios()
    {
        Lista = ConexionUsuarios.ListaUsuarios();
    }

    public ArrayList getLista() {
        Usuarios();
        return Lista;
    }

    public void setLista(ArrayList Lista) {
        this.Lista = Lista;
    }
    
}
