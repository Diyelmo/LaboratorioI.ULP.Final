/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.DataBase;

import compra2.Entidades.Proveedor;
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
public class ProveedorDataBase {
    private Connection con = null;

    public ProveedorDataBase() {

        con = Conexion.getConexion();
    }
    
     public void crearProveedor(Proveedor p) {
        String sql = "INSERT INTO `proveedor`( `razonSocial`, `domicilio`, `telefono`, estado) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getDomicilio());
            ps.setString(3, p.getTelefono());
            ps.setBoolean(4, p.isEstado());
              ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdProveedor(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Proveedor a sido añadido con exito");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Proveedor  " + ex.getMessage());
        }
    }

     public void modificarProducto(Proveedor p, String s) {
        String sql="UPDATE `proveedor` SET `razonSocial`=?,`domicilio`=?,`telefono`=?, estado=? where razonSocial=? ";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getDomicilio());
            ps.setString(3,p.getTelefono());
             ps.setBoolean(4,p.isEstado());
              ps.setString(3,s);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Stock actualizado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla proveedor" + ex.getMessage());
        }
           
    }
      public void modificarProductoPorId(Proveedor p, int s) {
        String sql="UPDATE `proveedor` SET `razonSocial`=?,`domicilio`=?,`telefono`=?, estado=? where idProveedor=? ";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getDomicilio());
            ps.setString(3,p.getTelefono());
            ps.setBoolean(4,p.isEstado());
             ps.setInt(5,s);
            
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "proveedor actualizado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla proveedor" + ex.getMessage());
        }
           
    }
     
      public Proveedor buscarProveedor(int id){
        String sql="select * from proveedor where idProveedor=? ";
        Proveedor p=null;
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                p=new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setDomicilio(rs.getString("domicilio"));
                p.setTelefono(rs.getString("telefono"));
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
      
      
               public ArrayList<Proveedor> listarProveedoresActivo() {
        String sql = "SELECT * FROM proveedor where estado=1  ";
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor prod = new Proveedor();
                prod.setIdProveedor(rs.getInt("idProveedor"));
                prod.setRazonSocial(rs.getString("razonSocial"));
                prod.setDomicilio(rs.getString("domicilio"));
                prod.setTelefono(rs.getString("telefono"));
              
                prod.setEstado(rs.getBoolean("estado"));
                
                proveedores.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return proveedores;

    }
                          public ArrayList<Proveedor> listarProveedoresInactivo() {
        String sql = "SELECT * FROM proveedor where estado=0  ";
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor prod = new Proveedor();
                prod.setIdProveedor(rs.getInt("idProveedor"));
                prod.setRazonSocial(rs.getString("razonSocial"));
                prod.setDomicilio(rs.getString("domicilio"));
                prod.setTelefono(rs.getString("telefono"));
              
                prod.setEstado(rs.getBoolean("estado"));
                
                proveedores.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return proveedores;

    }
                          
                          
}
