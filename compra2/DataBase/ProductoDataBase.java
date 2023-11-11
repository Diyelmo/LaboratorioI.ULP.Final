/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.DataBase;

import compra2.Entidades.Producto;
import compra2.Entidades.Proveedor;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author RODRI
 */
public class ProductoDataBase {
     private Connection con = null;

    public ProductoDataBase() {

        con = Conexion.getConexion();
    }
    
    public void agregarProducto(Producto p) {
        String sql = "INSERT INTO producto ( nombreProducto, descripcion, precioActual,stock, estado) "
                + "VALUES (?,?,?,?,?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecioActual());
            ps.setInt(4, p.getStock());
             ps.setBoolean(5, p.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdProducto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Producto añadido con exito");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto  " + ex.getMessage());
        }
    }
    
    public void bajaProducto(int id) {
        String sql = "UPDATE producto SET estado = 0 WHERE idProducto = ? ";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Producto");
        }
    }
    
    public void altaProducto(int id) {
        String sql = "UPDATE producto SET estado = 1 WHERE idProducto = ? ";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "  El producto esta disponible.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Producto");
        }
    }
    
    public void modificarProducto(Producto p, String s) {
        String sql="UPDATE `producto` SET `nombreProducto`=?,"
                    + "`descripcion`=?,`precioActual`=?,`stock`=?,`estado`=? where nombreProducto=?";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3,p.getPrecioActual());
            ps.setInt(4, p.getStock());
            ps.setBoolean(5,p.isEstado());
             ps.setString(6, s);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Producto modificado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
           
    }
  public void modificarProductoPorId(Producto p, int s) {
        String sql="UPDATE `producto` SET `nombreProducto`=?,"
                    + "`descripcion`=?,`precioActual`=?,`stock`=?,`estado`=? where idProducto=?";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3,p.getPrecioActual());
            ps.setInt(4, p.getStock());
            ps.setBoolean(5,p.isEstado());
             ps.setInt(6, s);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Producto modificado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
           
    }
  
  
         public Producto buscarProducto(int id){
        String sql="select * from producto where idProducto=? ";
        Producto p=null;
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                p=new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioActual(rs.getDouble("precioActual"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getBoolean("estado"));
               
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe ese Producto.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Producto");
        }
        return p;
    }
         
          public ArrayList<Producto> listarProductosTodos() {
        String sql = "SELECT * FROM producto  ";
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreProducto(rs.getString("nombreProducto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioActual(rs.getDouble("precioActual"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getBoolean("estado"));
                
                productos.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return productos;

    }
             public ArrayList<Producto> listarProductosActivos() {
        String sql = "SELECT * FROM producto where estado=1 ";
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreProducto(rs.getString("nombreProducto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioActual(rs.getDouble("precioActual"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getBoolean("estado"));
                
                productos.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return productos;

    }
                public ArrayList<Producto> listarProductosInactivos() {
        String sql = "SELECT * FROM producto where estado=0  ";
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreProducto(rs.getString("nombreProducto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioActual(rs.getDouble("precioActual"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getBoolean("estado"));
                
                productos.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return productos;

                }
                
                
                      public ArrayList<Producto> listarProductosPorFecha(LocalDate fecha) {
        String sql = " SELECT producto.* FROM `producto` " +
"inner join detallecompra on(producto.idProducto=detallecompra.idProducto) " +
"inner join compra on(compra.idCompra=detallecompra.idCompra) " +
"WHERE fecha=? ";
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setDate(1,java.sql.Date.valueOf(fecha) );
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreProducto(rs.getString("nombreProducto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioActual(rs.getDouble("precioActual"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getBoolean("estado"));
                
                productos.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return productos;

    }
        
         public ArrayList<Producto> listarProductosPorDejajoDelMinimo() {
        String sql = "SELECT * FROM producto where estado=1 and  stock < 5 ";
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreProducto(rs.getString("nombreProducto"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecioActual(rs.getDouble("precioActual"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getBoolean("estado"));
                
                productos.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return productos;

    }
           public void modificarProductoStock(Producto p, int cantidad, int idProducto) {
        String sql="UPDATE `producto` SET `stock`=? where idProducto=?";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
           
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Producto modificado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
           
    }
           
           
         public Producto buscarProductoPorNombre(String nombre){
        String sql="select * from producto where nombreProducto=? ";
        Producto p=null;
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                p=new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombreProducto(rs.getString("nombreProducto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioActual(rs.getDouble("precioActual"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getBoolean("estado"));
               
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe ese Producto.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Producto");
        }
        return p;
    }
}
