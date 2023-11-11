/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.Entidades;
import compra2.Entidades.Proveedor;
/**
 *
 * @author RODRI
 */
public class RelacionProductoProveedor {
    private int idRelacion;
    private Producto producto;
    private Proveedor proveedor;
    private boolean estado;
    public RelacionProductoProveedor() {
    }

    public RelacionProductoProveedor(int idRelacion, Producto producto, Proveedor proveedor,boolean estado) {
        this.idRelacion = idRelacion;
        this.producto = producto;
        this.proveedor = proveedor;
        this.estado=estado;
    }

    public RelacionProductoProveedor(Producto producto, Proveedor proveedor,boolean estado) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.estado=estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(int idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "RelacionProductoProveedor{" + "idRelacion=" + idRelacion + ", producto=" + producto + ", proveedor=" + proveedor + ", estado=" + estado + '}';
    }

   
    
    
}
