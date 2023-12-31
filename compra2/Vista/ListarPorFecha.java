/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package compra2.Vista;

import compra2.DataBase.ProductoDataBase;
import compra2.Entidades.Producto;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RODRI
 */
public class ListarPorFecha extends javax.swing.JInternalFrame {

     private DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int f, int c){
            return false;
        }
    };
    public ListarPorFecha() {
        initComponents();
        cabecera();
        llenarTablaInicio();
        dia.setDate(null);
        delimitarFecha();
        dia.setDate(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("Listar todos los productos comprados en la feche seleccionada");

        jLabel2.setText("Seleccione la fecha");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Reiniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton1)
                .addGap(61, 61, 61)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        borrarlista();
        try{
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            String fe = f.format(dia.getDate());
            LocalDate fecha = LocalDate.parse(fe, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            ProductoDataBase data=new ProductoDataBase();
            for (Producto o : data.listarProductosPorFecha(fecha)) {
            modelo.addRow(new Object[]{
         o.getIdProducto(),o.getNombreProducto(),o.getDescripcion(),o.getPrecioActual(),o.getStock()});
        }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha antes de buscar un producto");
            llenarTablaInicio();
            dia.setDate(null);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        borrarlista();
        dia.setDate(null);
        llenarTablaInicio();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
      private void cabecera(){
       
        modelo.addColumn("ID Producto");
          modelo.addColumn("Nombre Producto");
          modelo.addColumn(" Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
       
        jTable1.setModel(modelo);   
    }
public void borrarlista(){
        int a=modelo.getRowCount()-1;
            for(int i=a;i>=0;i--){
             modelo.removeRow(i);
            }
 } 

public void delimitarFecha(){


// Crear un objeto Calendar para representar la fecha de inicio (1 de enero de 2023)
Calendar fechaInicio = Calendar.getInstance();
fechaInicio.set(2023, Calendar.OCTOBER, 1);

// Crear un objeto Calendar para representar la fecha de fin (31 de diciembre de 2023)
Calendar fechaFin = Calendar.getInstance();


// Convertir los objetos Calendar a objetos Date
Date fechaInicioDate = fechaInicio.getTime();
Date fechaFinDate = fechaFin.getTime();

// Establecer el rango de fechas seleccionables en el JDateChooser
dia.setSelectableDateRange(fechaInicioDate, fechaFinDate);

}
    public void llenarTablaInicio(){
         ProductoDataBase data=new ProductoDataBase();
            for (Producto o : data.listarProductosActivos()) {
            modelo.addRow(new Object[]{
         o.getIdProducto(),o.getNombreProducto(),o.getDescripcion(),o.getPrecioActual(),o.getStock()});
        }
    }

    
    
    // pruebaa
    
}
