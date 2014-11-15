package Beans;

import BD.DaoProductos;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private float precio;
    private int estado;
    private DaoProductos bd;

    public Producto (){}
    public Producto ( int i, String nom, String desc, int sto, float prec, int est)
    {
        this.id =i;
        this.nombre = nom;
        this.descripcion = desc;
        this.stock = sto;
        this.precio  = prec;
        this.estado = est;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
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
    public DaoProductos getBd() throws Exception 
    
    {
        if (this.bd == null) 
        
        {
            this.bd = new DaoProductos();
        }
        return bd;
    }
    
}
