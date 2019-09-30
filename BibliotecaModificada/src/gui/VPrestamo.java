/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class VPrestamo extends javax.swing.JDialog {
     private aplicacion.FachadaAplicacion fa;
     private Integer idLibro;
     private Integer idEjemplar;
     boolean estaPrestado;
    /**
     * Creates new form VPrestamo
     */
    public VPrestamo(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa, Integer libro, Integer ejemplar) {
        super(parent, modal);
        initComponents();
        this.fa=fa;
        this.idEjemplar=ejemplar;
        this.idLibro=libro;
        botonPrestar.setEnabled(false);
        estaPrestado=false;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EtiquetaId = new javax.swing.JLabel();
        textoId = new javax.swing.JTextField();
        etiquetaNombre = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        botonPrestar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        EtiquetaId.setText("Id:");

        textoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoIdActionPerformed(evt);
            }
        });

        etiquetaNombre.setText("Nombre:");

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        tablaUsuarios.setModel(new ModeloTablaPrestamos());
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);
        tablaUsuarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        botonPrestar.setText("Prestar");
        botonPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPrestarActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EtiquetaId)
                        .addGap(2, 2, 2)
                        .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(etiquetaNombre)
                        .addGap(2, 2, 2)
                        .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonPrestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EtiquetaId)
                    .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonSalir)
                    .addComponent(botonPrestar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoIdActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        buscarUsuarios();
        fa.guardarBusqueda((ArrayList)((ModeloTablaPrestamos)tablaUsuarios.getModel()).getFilas(), ((ModeloTablaPrestamos)tablaUsuarios.getModel()).getVencidos());
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPrestarActionPerformed
        if(!tablaUsuarios.getSelectionModel().isSelectionEmpty()){       
            fa.prestar((String)tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0),this.idLibro,this.idEjemplar);
            this.estaPrestado=fa.estaPrestado(idLibro, idEjemplar);
            botonPrestar.setEnabled(!estaPrestado);
        }
       
    }//GEN-LAST:event_botonPrestarActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        if(!tablaUsuarios.getSelectionModel().isSelectionEmpty()){       
            if((Integer)tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(),3)>0){
                botonPrestar.setEnabled(false);
            }else if (!estaPrestado){
               botonPrestar.setEnabled(true); 
            }
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EtiquetaId;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonPrestar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField textoId;
    private javax.swing.JTextField textoNombre;
    // End of variables declaration//GEN-END:variables

    public void buscarUsuarios(){
        ModeloTablaPrestamos m;

        m=(ModeloTablaPrestamos) tablaUsuarios.getModel();
        m.setFilas(fa.obtenerUsuario(textoId.getText(),textoNombre.getText()),fa.vencidos());
        if (m.getRowCount() > 0) {
            tablaUsuarios.setRowSelectionInterval(0, 0);
                botonPrestar.setEnabled(true);
            if((Integer)tablaUsuarios.getValueAt(0,3)>0 || estaPrestado){
                botonPrestar.setEnabled(false);
            }
        }
        
    }

}

