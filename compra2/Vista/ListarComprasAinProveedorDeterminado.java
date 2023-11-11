/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package compra2.Vista;

import compra2.DataBase.DetalleCompraDataBase;
import compra2.Entidades.DetalleCompra;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RODRI
 */
public class ListarComprasAinProveedorDeterminado extends javax.swing.JInternalFrame {

       private DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int f, int c){
            return false;
        }
    };
    public ListarComprasAinProveedorDeterminado() {
        initComponents();
        cabecera();
        LlenarTabla();
        
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
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Listar compras de un determinado producto");

        jLabel2.setText("Ingrese el producto a listar");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

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

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reinicio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(53, 53, 53)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // Evento de tecla
        borrarlista();
        llenarTabla();
       
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextField1.setText("");
        borrarlista();
         LlenarTabla();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
 private void llenarTabla() {
        DetalleCompraDataBase pd=new DetalleCompraDataBase();
        String x=jTextField1.getText();
        borrarlista();
        for (DetalleCompra p : pd.listarDetalleCompraTodos()) {
            if(p.getProducto().getNombreProducto().startsWith(jTextField1.getText()) ){
            modelo.addRow(new Object[]{
                p.getCompra().getIdCompra(),p.getCompra().getProveedor().getIdProveedor(),p.getProducto().getIdProducto(),
                p.getProducto().getNombreProducto(),p.getCantidad(),p.getPrecioCosto(),p.getCompra().getFecha(),p.isEstado()});
        }
//            if(p.getNombre().startsWith(jTextField1.getText()) && !p.isCobrada()){
//            modelo.addRow(new Object[]{
//                p.getIdpedido(),p.getIdmesa().getNumero(),p.getNombre(),p.getFecha(),p.getHora(),p.getImporte(),"Pago pendiente"});
//        }
           
    }
     
    }
 public void borrarlista(){
        int a=modelo.getRowCount()-1;
            for(int i=a;i>=0;i--){
             modelo.removeRow(i);
            }
 }
            private void cabecera(){
        modelo.addColumn("ID Compra");
        modelo.addColumn("ID Proveedor");
        modelo.addColumn("ID Producto");
     
        modelo.addColumn("Nombre Producto");
           modelo.addColumn(" Cantidad");
              modelo.addColumn(" Costo/Precio");
        modelo.addColumn("Fecha de compra");
        modelo.addColumn("Estado");
       
        jTable1.setModel(modelo);   
    }
    
  public void LlenarTabla(){
           DetalleCompraDataBase pd=new DetalleCompraDataBase();
        String x=jTextField1.getText();
        borrarlista();
          if(x.isEmpty()){
          for (DetalleCompra p : pd.listarDetalleCompraTodos()) {
            if(p.getProducto().getNombreProducto().startsWith(jTextField1.getText()) ){
            modelo.addRow(new Object[]{
                p.getCompra().getIdCompra(),p.getCompra().getProveedor().getIdProveedor(),p.getProducto().getIdProducto(),
                p.getProducto().getNombreProducto(),p.getCantidad(),p.getPrecioCosto(),p.getCompra().getFecha(),p.isEstado()});
        }
          
    }
}
  }
}
