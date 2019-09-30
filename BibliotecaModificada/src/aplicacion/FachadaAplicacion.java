/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GesionLibros cl;
    GestionUsuarios cu;
    GestionCategorias cc;
    GestionPrestamos cp;
    
    
 public FachadaAplicacion(){
   fgui=new gui.FachadaGui(this);
   fbd= new baseDatos.FachadaBaseDatos(this);
   cl= new GesionLibros(fgui, fbd);
   cu= new GestionUsuarios(fgui, fbd);
   cc= new GestionCategorias(fgui,fbd);
   cp = new GestionPrestamos(fgui,fbd);
 }

 public static void main(String args[]) {
     FachadaAplicacion fa;
     
     fa= new FachadaAplicacion();
     fa.iniciaInterfazUsuario();
 }
 
 public gui.FachadaGui getFachadaGui(){
     return this.fgui;
 }
 public void iniciaInterfazUsuario(){
     fgui.iniciaVista();
 }

 public void muestraExcepcion(String e){
     fgui.muestraExcepcion(e);
 }
 
public java.util.List<Categoria> obtenerCategorias(){
    return cc.obtenerCategorias();
}
public void borrarCategoria(String nombre){
    cc.borrarCategoria(nombre);
}
public void anhadirCategoria(String nombre, String descripcion){
    cc.anhadirCategoria(nombre, descripcion);
}
public java.util.List<Libro> obtenerLibros(Integer id, String titulo, String isbn, String autor){
  return cl.obtenerLibros(id, titulo,  isbn,  autor);
};

public void visualizarLibro(Integer idLibro){
 cl.visualizarLibro(idLibro);
}

public void nuevoLibro(){
 cl.nuevoLibro();
}

public Integer actualizarLibro(Libro l){
  return cl.actualizarLibro(l);
}

public void borrarLibro(Integer idLibro){
   cl.borrarLibro(idLibro);
}

public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
 cl.actualizarCategoriasLibro(idLibro, categorias);
}

public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares, java.util.List<Integer> borrar){
  return cl.actualizarEjemplaresLibro(idLibro, ejemplares, borrar);
}


public Boolean comprobarAutentificacion(String idUsuario, String clave){
  return cu.comprobarAutentificacion(idUsuario, clave);
}
 
public java.util.List<Usuario> obtenerUsuario(String id, String nombre){
  return cu.obtenerUsuarios(id,nombre);
}

public void borrarUsuario(Usuario usuario){
   cu.borrarUsuario(usuario);
}
public void nuevoUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
    cu.nuevoUsuario(id,clave,nombre,direccion,email,tipo);
}
public void actualizarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
    cu.actualizarUsuario(id,clave,nombre,direccion,email,tipo);
}
public void prestar(String usuario, int libro, int ejemplar){
    cp.prestar(usuario, libro, ejemplar);
}
public void devolver(int libro, int ejemplar){
    cp.devolver(libro,ejemplar);
}
public HashMap<String,Integer> vencidos(){
       return cp.vencidos();
    }
public boolean estaPrestado(int libro, int ejemplar){
    return cp.estaPrestado(libro,ejemplar);
}
public HashMap<Integer,Prestamo> prestados(int libro){
        return cp.prestados(libro);
    }
public boolean existe(int libro, int ejemplar){
        return cp.existe(libro, ejemplar);
    }
public void guardarBusqueda(ArrayList<Usuario> usuarios, HashMap<String,Integer> vencidos){
     cp.guardarBusqueda(usuarios,vencidos);
}

}
