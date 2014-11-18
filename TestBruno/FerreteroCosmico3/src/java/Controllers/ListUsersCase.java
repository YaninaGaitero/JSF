/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import DAOs.*;
import Modelo.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public ArrayList getLista()
    {
        try 
        {
            ConexionUsuarios.conectar();
            Usuarios();
            return Lista;
        } 
        catch (Exception ex) {
            Logger.getLogger(ListUsersCase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally
        {
            ConexionUsuarios.desconectar();
        }
    }

    public void setLista(ArrayList Lista) {
        this.Lista = Lista;
    }
    
}
