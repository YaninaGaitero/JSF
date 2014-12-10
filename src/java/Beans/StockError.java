/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author usuario
 */
public class StockError 
{
    private int stock;
    private int compra;
    private String Producto;

    public StockError() 
    {
        this.compra = 0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    @Override
    public String toString() 
    {
        return "StockError{" + " La compra Nº "+ compra  + 
                " no pudo ser procesada debido a faltante de stock en el Producto: "
                + Producto + ". Stock Requerido: " + stock + '}';
    }
    
    
    
}
