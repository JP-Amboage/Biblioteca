/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumnogreibd
 */
public class GestionPrestamos {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionPrestamos(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    public void prestar(String usuario, int libro, int ejemplar){
        fbd.prestar(usuario,libro,ejemplar);
    }
    public void devolver(int libro, int ejemplar){
        fbd.devolver(libro,ejemplar);
    }
    public HashMap<String,Integer> vencidos(){
       return fbd.vencidos();
    }
    public boolean estaPrestado(int libro, int ejemplar){
        return fbd.estaPrestado(libro,ejemplar);
    }
    public HashMap<Integer,Prestamo> prestados(int libro){
        return fbd.prestados(libro);
    }
    public boolean existe(int libro, int ejemplar){
        return fbd.existe(libro, ejemplar);
    }
    public void guardarBusqueda(ArrayList<Usuario> usuarios, HashMap<String,Integer> vencidos){
     fbd.guardarBusqueda(usuarios,vencidos);
    }
}
