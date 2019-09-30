/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
/**
 *
 * @author basesdatos
 */
public class GestionUsuarios {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    
  public Boolean comprobarAutentificacion(String idUsuario, String clave){
      Usuario u;

      u=fbd.validarUsuario(idUsuario, clave);
      if (u!=null){
          return u.getTipoUsuario()==TipoUsuario.Administrador;
      } else return false;
  }
  
  public java.util.List<Usuario> obtenerUsuarios(String id, String nombre){
      return fbd.consultarUsuarios(id, nombre);
  }
  
  public void borrarUsuario(Usuario usuario){
      fbd.borrarUsuario(usuario);
  }
  public void nuevoUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
      fbd.nuevoUsuario(id,clave,nombre,direccion,email,tipo);
  }
  public void actualizarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
      fbd.actualizarUsuario(id,clave,nombre,direccion,email,tipo);
  }
  
 
  
}
