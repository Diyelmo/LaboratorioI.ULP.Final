/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.DataBase;

import compra2.DataBase.Conexion;
import compra2.DataBase.ProveedorDataBase;
import compra2.Entidades.Compra;
import compra2.Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author RODRI
 */
public class CompraDataBase {
     private Connection con = null;

    public CompraDataBase() {

        con = Conexion.getConexion();
    }
    
    public void crearCompra(Compra p) {
        String sql = "INSERT INTO compra ( idProveedor, `fecha`, estado) "
                + "VALUES (?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, p.getProveedor().getIdProveedor());
            ps.setDate(2, Date.valueOf(p.getFecha()));
            ps.setBoolean(3, p.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdCompra(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Compra añadida con exito");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Compra  " + ex.getMessage());
        }
    }
      public void modificarProductoIdProveedor(Compra p, int s) {
        String sql="UPDATE `producto` SET `idProveedor`=?,"
                    + "fecha=?, estado=? where idProveedor=?";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getProveedor().getIdProveedor());
            ps.setDate(2, Date.valueOf(p.getFecha()));
            ps.setBoolean(3,p.isEstado());
            ps.setInt(4,s);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Compra actualizada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Compra" + ex.getMessage());
        }
           
    }
    
      public void modificarProductoPorId(Compra p, int id) {
        String sql="UPDATE `producto` SET `idProveedor`=?,"
                    + "fecha=?, estado=? where idCompra=?";
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getProveedor().getIdProveedor());
            ps.setDate(2, Date.valueOf(p.getFecha()));
            ps.setBoolean(3,p.isEstado());
            ps.setInt(4,id);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Compra actualizada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Compra" + ex.getMessage());
        }
           
    }
      
      public Compra buscarCompraPoriD(int id){
          String sql="SELECT * FROM `compra` WHERE idCompra=?";
          Compra c=null;
          ProveedorDataBase db=new ProveedorDataBase();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                c=new Compra();
                c.setIdCompra(rs.getInt("idCompra"));
               Proveedor p= db.buscarProveedor(rs.getInt("idProveedor"));
                c.setProveedor(p);
                c.setFecha(rs.getDate("fecha").toLocalDate());
                c.setEstado(rs.getBoolean("estado"));
              
                
            }else{
                JOptionPane.showMessageDialog(null, " Compra no encontrada.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Compra");
        }
        return c;
    }
    
      public ArrayList<Compra> buscarCompraPorFecha(LocalDate fecha){
          String sql="SELECT * FROM `compra` WHERE fecha=?";
          Compra c=new Compra();
          ProveedorDataBase db=new ProveedorDataBase();
          ArrayList<Compra> lista=new ArrayList();
          try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                c=new Compra();
                c.setIdCompra(rs.getInt("idCompra"));
               Proveedor p= db.buscarProveedor(rs.getInt("idProveedor"));
                c.setProveedor(p);
                c.setFecha(rs.getDate("fecha").toLocalDate());
                c.setEstado(rs.getBoolean("estado"));
              
                lista.add(c);
           
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Compra");
        }
        return lista;
      }
    public ArrayList<Compra> listarCompras(){
          String sql="SELECT * FROM `compra` ";
          Compra c=new Compra();
          ProveedorDataBase db=new ProveedorDataBase();
          ArrayList<Compra> lista=new ArrayList();
          try {           
            PreparedStatement ps = con.prepareStatement(sql);
          
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                c=new Compra();
                c.setIdCompra(rs.getInt("idCompra"));
               Proveedor p= db.buscarProveedor(rs.getInt("idProveedor"));
                c.setProveedor(p);
                c.setFecha(rs.getDate("fecha").toLocalDate());
                c.setEstado(rs.getBoolean("estado"));
              
                lista.add(c);
           
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Compra");
        }
        return lista;
      }
    
}
