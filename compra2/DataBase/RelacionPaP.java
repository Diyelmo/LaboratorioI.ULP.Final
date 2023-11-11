/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.DataBase;

import compra2.Entidades.DetalleCompra;
import compra2.Entidades.Producto;
import compra2.Entidades.Proveedor;
import compra2.Entidades.RelacionProductoProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RODRI
 */
public class RelacionPaP {
   
     private Connection con = null;

    public RelacionPaP() {

        con = Conexion.getConexion();
    }
    
    public void crearRelacion(int idproducto, int idproveedor){
         String sql = "INSERT INTO `relacion`( `idProducto`, `IdProveedor`, estado) VALUES (?,?,1)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idproducto);
            ps.setInt(2, idproveedor);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                rs.getInt(1);
                JOptionPane.showMessageDialog(null, "Relacion añadida con exito");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Relacion  " + ex.getMessage());
            }
    }
    
     public void modificarRelacionPaP(RelacionProductoProveedor pp,int idRelacion) {
        String sql = "UPDATE `relacion` SET idProducto='?',IdProveedor='?',estado=? where idRelacion=? ";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pp.getProducto().getIdProducto());
            ps.setInt(2, pp.getProveedor().getIdProveedor());
            ps.setBoolean(3, pp.isEstado());
            ps.setInt(4, idRelacion);
           
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, " La relacion ha sido modificada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Relacion");
        }
    }
     
     
      public RelacionProductoProveedor buscarrelacion(int idRelacion){
        String sql="SELECT * FROM `relacion` inner join producto on producto.idProducto=relacion.idProducto join  proveedor on proveedor.idProveedor=relacion.IdProveedor where idRelacion=? ";
      
      RelacionProductoProveedor pp=new RelacionProductoProveedor();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idRelacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Proveedor proveedor=new Proveedor();
                Producto pro=new Producto();
               pro.setIdProducto(rs.getInt("idProducto"));
                pro.setDescripcion(rs.getString("descripcion"));
                 pro.setNombreProducto(rs.getString("nombreProducto"));
                  pro.setPrecioActual(rs.getDouble("precioActual"));
                   pro.setStock(rs.getInt("stock"));
                    pro.setEstado(rs.getBoolean("estado"));
                pp.setProducto(pro);
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setRazonSocial(rs.getString("razonSocial"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEstado(rs.getBoolean("estado"));
                pp.setProveedor(proveedor);
                pp.setEstado(rs.getBoolean("estado"));
              
               
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe esa Relacion.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Relacion");
        }
        return pp;
    }
      
      
      public ArrayList<RelacionProductoProveedor>listarrelacionActivas(){
        String sql="SELECT * FROM `relacion` inner join producto on (producto.idProducto=relacion.idProducto) "
                + "inner join  proveedor on (proveedor.idProveedor=relacion.IdProveedor)";
                
      
      RelacionProductoProveedor pp = null;
      ArrayList< RelacionProductoProveedor >lista=new ArrayList<RelacionProductoProveedor>();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 pp=new RelacionProductoProveedor();
                Proveedor proveedor=new Proveedor();
                Producto pro=new Producto();
                pp.setIdRelacion(rs.getInt("idRelacion"));
               pro.setIdProducto(rs.getInt("idProducto"));
                pro.setDescripcion(rs.getString("descripcion"));
                 pro.setNombreProducto(rs.getString("nombreProducto"));
                  pro.setPrecioActual(rs.getDouble("precioActual"));
                   pro.setStock(rs.getInt("stock"));
                    pro.setEstado(rs.getBoolean("estado"));
                pp.setProducto(pro);
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setRazonSocial(rs.getString("razonSocial"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEstado(rs.getBoolean("estado"));
                pp.setProveedor(proveedor);
                pp.setEstado(rs.getBoolean("estado"));
            
            lista.add(pp);
            
            }
           
              
               
                
            
            
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Relacion");
        }
        return lista;
    }
}
