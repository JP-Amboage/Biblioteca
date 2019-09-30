/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Usuario;
import aplicacion.Categoria;
import aplicacion.Libro;
import aplicacion.Prestamo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOLibros daoLibros;
    private DAOCategorias daoCategorias;
    private DAOUsuarios daoUsuarios;
    private DAOprestamos daoPrestamos;

    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);

            daoLibros = new DAOLibros(conexion, fa);
            daoCategorias = new DAOCategorias(conexion, fa);
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoPrestamos= new DAOprestamos(conexion, fa);
          


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
        
        
        
    }
    
    

    public java.util.List<Libro> consultarCatalogo(Integer id, String titulo, String isbn, String autor){
        return daoLibros.consultarCatalogo(id, titulo, isbn, autor);
    }

    public Libro consultarLibro(Integer idLibro){
        return daoLibros.consultarLibro(idLibro);
    }
    public java.util.List<Ejemplar> consultarEjemplaresLibro(Integer idLibro){
        return daoLibros.consultarEjemplaresLibro(idLibro);
    }
    public java.util.List<String> obtenerRestoCategorias(Integer idLibro){
        return daoLibros.obtenerRestoCategorias(idLibro);
    }
    public Integer insertarLibro(Libro libro){
       return daoLibros.insertarLibro(libro);
    }
    public void borrarLibro(Integer idLibro){
        daoLibros.borrarLibro(idLibro);
    }
    public void modificarLibro(Libro libro){
         daoLibros.modificarLibro(libro);
    }
    public void modificarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
       daoLibros.modificarCategoriasLibro(idLibro, categorias);
    }
    public void insertarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.insertarEjemplarLibro(idLibro, ejemplar);
    }
    public void borrarEjemplaresLibro(Integer idLibro, java.util.List<Integer> numsEjemplar){
        daoLibros.borrarEjemplaresLibro(idLibro, numsEjemplar);
    }
    public void modificarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.modificarEjemplarLibro(idLibro, ejemplar);
    }

    public Usuario validarUsuario(String idUsuario, String clave){
        return daoUsuarios.validarUsuario(idUsuario, clave);
    }
    public java.util.List<Usuario> consultarUsuarios(String id, String nombre){
        return daoUsuarios.consultarUsuarios(id, nombre);
    }
   
    public java.util.List<Categoria> consultarCategorias(){
        return daoCategorias.consultarCategorias();
    }
    public void anhadirCategoria(String nombre, String descripcion){
        daoCategorias.anhadirCategoria(nombre,descripcion);
    }
    public void borrarCategoria(String nombre){
        daoCategorias.borrarCategoria(nombre);
    }
    public void borrarUsuario(Usuario usuario){
        daoUsuarios.borrarUsuario(usuario);
    }
    public void nuevoUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
      daoUsuarios.nuevoUsuario(id,clave,nombre,direccion,email,tipo);
    }
    public void actualizarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
      daoUsuarios.actualizarUsuario(id,clave,nombre,direccion,email,tipo);
    }
    public void prestar(String usuario, int libro, int ejemplar){
        daoPrestamos.prestar(usuario, libro, ejemplar);
    }
    
    public void devolver(int libro, int ejemplar){
        daoPrestamos.devolver(libro,ejemplar);
    }
    public HashMap<String,Integer> vencidos(){
       return daoPrestamos.vencidos();
    }
    public boolean estaPrestado(int libro, int ejemplar){
        return daoPrestamos.estaPrestado(libro,ejemplar);
    }
    public HashMap<Integer,Prestamo> prestados(int libro){
        return daoPrestamos.prestado(libro);
    }
    public boolean existe(int libro, int ejemplar){
        return daoPrestamos.existe(libro, ejemplar);
    }
    public void guardarBusqueda(ArrayList<Usuario> usuarios, HashMap<String,Integer> vencidos){
     daoPrestamos.guardarBusqueda(usuarios,vencidos);
    }
}
