/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import BD.DaoFacturas;
import BD.DatosUsuario;

/**
 *
 * @author Ezequiel
 */
public class ControladoraCompra
{
    DaoFacturas DaoCompra = new DaoFacturas();
    
    public ControladoraCompra()throws Exception{
        
    }
    public Double GetTotal(int IDCompra)throws Exception
    {
        return DaoCompra.getTotal(IDCompra);       
    }
}
