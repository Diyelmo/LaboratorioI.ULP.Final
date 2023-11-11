/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compra2.DataBase;

import compra2.DataBase.CompraDataBase;
import compra2.Entidades.Compra;
import compra2.Entidades.DetalleCompra;
import compra2.Entidades.Producto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RODRI
 */
public class DetalleCompraDataBase {
     private Connection con = null;
     private static ArrayList <Integer> masVendido=new ArrayList<>();
    public DetalleCompraDataBase() {

        con = Conexion.getConexion();
    }

    public static ArrayList<Integer> getMasVendido() {
        return masVendido;
    }

    public static void setMasVendido(Integer cantidad) {
        masVendido.add(cantidad);
    }

   
    
    public void crearDetalleCompra(DetalleCompra p) {
        String sql = "INSERT INTO detallecompra( cantidad, precioCosto, idCompra, idProducto, estado) "
                + "VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, p.getCantidad());
            ps.setDouble(2, p.getPrecioCosto());
            ps.setInt(3, p.getCompra().getIdCompra());
            ps.setInt(4, p.getProducto().getIdProducto());
            ps.setBoolean(5, p.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdDetalle(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Detalle de compra añadida con exito");
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Detalle Compra  " + ex.getMessage());
        }
        
    }
 
     public void modificarDetalleCompra(DetalleCompra d,int id) {
        String sql = "UPDATE `detallecompra` SET `cantidad`='?',`precioCosto`='?',estado=? where idDetalle=? ";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, d.getCantidad());
            ps.setDouble(2, d.getPrecioCosto());
            ps.setBoolean(3, d.isEstado());
            ps.setInt(4, d.getIdDetalle());
           
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, " El detalle de la compra ha sido modificado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla DetalleCompra");
        }
    }

             public DetalleCompra buscarDetalleCompra(int id){
        String sql="select * from detalleCompra  where idDetalle=? ";
       DetalleCompra p=null;
       CompraDataBase db=new CompraDataBase();
       ProductoDataBase d=new ProductoDataBase();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Compra c=new Compra();
                Producto pro=new Producto();
                p=new DetalleCompra();
                p.setIdDetalle(rs.getInt("idDetalle"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setPrecioCosto(rs.getDouble("precioCosto"));
                c=db.buscarCompraPoriD(rs.getInt("idCompra"));
                p.setCompra(c);
                pro=d.buscarProducto(rs.getInt("idProducto"));
                p.setProducto(pro);
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
             
             public int ultimoId(){
                 int id = 0;
      try {
            
            
           
           
            
            Statement stmt = con.createStatement();
            

            // Obtén el último valor autoincremental insertado
            String getLastInsertIdQuery = "SELECT LAST_INSERT_ID()";
            ResultSet rs = stmt.executeQuery(getLastInsertIdQuery);

            if (rs.next()) {
                 id = rs.getInt(1);
               
            }

            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
      return id;
    }
             
             
             
                    public ArrayList<DetalleCompra> listarDetalleCompraTodos() {
        String sql = "SELECT * FROM detallecompra  ";
        ArrayList<DetalleCompra> detalleCompra = new ArrayList<DetalleCompra>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCompra prod = new DetalleCompra();
                CompraDataBase comp=new CompraDataBase();
                ProductoDataBase pr=new ProductoDataBase();
                prod.setIdDetalle(rs.getInt("idDetalle"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setPrecioCosto(rs.getDouble("precioCosto"));
                Compra c=new Compra();
                c=comp.buscarCompraPoriD(rs.getInt("idCompra"));
                prod.setCompra(c);
                Producto z=new Producto();
                z=pr.buscarProducto(rs.getInt("idProducto"));
                prod.setProducto(z);
                
                prod.setEstado(rs.getBoolean("estado"));
                
                detalleCompra.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return detalleCompra;

                    }
                    
                       public ArrayList<DetalleCompra> listarDetalleCompraActivos() {
        String sql = "SELECT * FROM detallecompra where estado=1 ";
        ArrayList<DetalleCompra> detalleCompra = new ArrayList<DetalleCompra>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCompra prod = new DetalleCompra();
                CompraDataBase comp=new CompraDataBase();
                ProductoDataBase pr=new ProductoDataBase();
                prod.setIdDetalle(rs.getInt("idDetalle"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setPrecioCosto(rs.getDouble("precioCosto"));
                Compra c=new Compra();
                c=comp.buscarCompraPoriD(rs.getInt("idCompra"));
                prod.setCompra(c);
                Producto z=new Producto();
                z=pr.buscarProducto(rs.getInt("idProducto"));
                prod.setProducto(z);
                
                prod.setEstado(rs.getBoolean("estado"));
                
                detalleCompra.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return detalleCompra;

    }           
                     public ArrayList<DetalleCompra> listarDetalleCompraInactivos() {
        String sql = "SELECT * FROM detallecompra where estado=0  ";
        ArrayList<DetalleCompra> detalleCompra = new ArrayList<DetalleCompra>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCompra prod = new DetalleCompra();
                CompraDataBase comp=new CompraDataBase();
                ProductoDataBase pr=new ProductoDataBase();
                prod.setIdDetalle(rs.getInt("idDetalle"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setPrecioCosto(rs.getDouble("precioCosto"));
                Compra c=new Compra();
                c=comp.buscarCompraPoriD(rs.getInt("idCompra"));
                prod.setCompra(c);
                Producto z=new Producto();
                z=pr.buscarProducto(rs.getInt("idProducto"));
                prod.setProducto(z);
                
                prod.setEstado(rs.getBoolean("estado"));
                
                detalleCompra.add(prod);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto" + ex.getMessage());
        }
        return detalleCompra;

    } 
                     
      public ArrayList<Producto> productoMasCompradoEntreFechas(LocalDate fecha, LocalDate fecha2){
          String sql="SELECT  SUM(cantidad)total, idProducto " +
"FROM `detallecompra` inner join compra on(compra.idCompra=detallecompra.idCompra) " +
"WHERE fecha BETWEEN ? and ? " +
"group by idProducto  ;";
           Producto p;
           ProductoDataBase data=new ProductoDataBase();
           ArrayList<Producto> lista=new ArrayList();
           try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(fecha) );
             ps.setDate(2,Date.valueOf(fecha2) );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
               
                Integer cantidad;
                cantidad=rs.getInt("total");
                
                p=data.buscarProducto(rs.getInt("idProducto"));
                
                this.setMasVendido(cantidad);
               lista.add(p);
               
                
         
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Pepe");
        }
        return lista;
      }               
      public ArrayList<Producto> productosDeCompra(int idCompra){//despues de buscar las compras por fecha y haber seleccionado una compra en particular
          //buscamos con el idcompra en detalle compra los productos relacionados con esa compra.
          String sql="SELECT * FROM `detallecompra` where idCompra=? " ;


           Producto p;
           ProductoDataBase data=new ProductoDataBase();
           ArrayList<Producto> lista=new ArrayList();
           try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,idCompra );
             
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
               
              
                
                p=data.buscarProducto(rs.getInt("idProducto"));
                
               
               lista.add(p);
               
                
         
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Pepe");
        }
        return lista;
      }               
      
      
       public ArrayList<Producto> productoMasComprado(){
          String sql="SELECT  SUM(cantidad)total, idProducto " +
"FROM `detallecompra` inner join compra on(compra.idCompra=detallecompra.idCompra) " +
 
"group by idProducto  ;";
           Producto p;
           ProductoDataBase data=new ProductoDataBase();
           ArrayList<Producto> lista=new ArrayList();
           try {           
            PreparedStatement ps = con.prepareStatement(sql);
           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
               
                Integer cantidad;
                cantidad=rs.getInt("total");
                
                p=data.buscarProducto(rs.getInt("idProducto"));
                
                this.setMasVendido(cantidad);
               lista.add(p);
               
                
         
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Pepe");
        }
        return lista;
      }               
}







