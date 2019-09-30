/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.Usuario;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaPrestamos extends AbstractTableModel {
    
    private java.util.List<Usuario> usuarios;
    private java.util.HashMap<String,Integer> pendientes;
    
    public ModeloTablaPrestamos(){
        this.usuarios=new java.util.ArrayList<Usuario>();
        this.pendientes=new java.util.HashMap<String, Integer>();
    }
    public Usuario getUsuario(int index){
        return usuarios.get(index);
    }

    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return usuarios.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Id"; break;
            case 1: nombre= "nombre"; break;
            case 2: nombre="email"; break;
            case 3: nombre= "vencidos"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.Integer.class; break;
        }
        return clase;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= usuarios.get(row).getIdUsuario(); break;
            case 1: resultado= usuarios.get(row).getNombre(); break;
            case 2: resultado=usuarios.get(row).getEmail();break;
            case 3: 
                if (pendientes.get(usuarios.get(row).getIdUsuario())!=null){
                    resultado= pendientes.get(usuarios.get(row).getIdUsuario());
                }else resultado=0;
                break;
        }
        return resultado;
    }

    @Override
    
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public void setFilas(java.util.List<Usuario> usuarios, java.util.HashMap<String,Integer> pendientes){
        this.usuarios=usuarios;
        this.pendientes=pendientes;
        fireTableDataChanged();
    }

    public void nuevoEjemplar(Usuario e){
        this.usuarios.add(e);
        fireTableRowsInserted(this.usuarios.size()-1, this.usuarios.size()-1);
    }

    public void borrarEjemplar(int indice){
        this.usuarios.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Usuario> getFilas(){
        return this.usuarios;
    }
    public java.util.HashMap<String,Integer> getVencidos(){       
        return this.pendientes;
    }

    public Usuario obtenerEjemplar(int i){
        return this.usuarios.get(i);
    }
}

