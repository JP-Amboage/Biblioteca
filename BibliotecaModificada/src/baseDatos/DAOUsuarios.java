/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.Usuario;
import aplicacion.TipoUsuario;
import java.sql.*;
/**
 *
 * @author basesdatos
 */
public class DAOUsuarios extends AbstractDAO {

   public DAOUsuarios (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario validarUsuario(String idUsuario, String clave){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
        stmUsuario=con.prepareStatement("select id_usuario, clave, nombre, direccion, email, tipo_usuario "+
                                        "from usuario "+
                                        "where id_usuario = ? and clave = ?");
        stmUsuario.setString(1, idUsuario);
        stmUsuario.setString(2, clave);
        rsUsuario=stmUsuario.executeQuery();
        if (rsUsuario.next())
        {
            resultado = new Usuario(rsUsuario.getString("id_usuario"), rsUsuario.getString("clave"),
                                      rsUsuario.getString("nombre"), rsUsuario.getString("direccion"),
                                      rsUsuario.getString("email"), TipoUsuario.valueOf(rsUsuario.getString("tipo_usuario")));

        }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public java.util.List<Usuario> consultarUsuarios(String id, String nombre){
        java.util.List<Usuario> resultado = new java.util.ArrayList<Usuario>();
        Usuario usuarioActual;
        Connection con;
        PreparedStatement stmCatalogo=null;
        ResultSet rsCatalogo;
        PreparedStatement stmAutores=null;
        ResultSet rsAutores;     

        con=this.getConexion();
        
        String consulta = "select * " +
                                         "from usuario as u "+
                                         "where nombre like ?"+
                                         "  and id_usuario like ?";
        try  {
         stmCatalogo=con.prepareStatement(consulta);
         stmCatalogo.setString(1, "%"+nombre+"%");
         stmCatalogo.setString(2, "%"+id+"%");
         rsCatalogo=stmCatalogo.executeQuery();
        while (rsCatalogo.next())
        {
            usuarioActual = new Usuario(rsCatalogo.getString("id_usuario"), rsCatalogo.getString("clave"),
                                      rsCatalogo.getString("nombre"), rsCatalogo.getString("direccion"),
                                      rsCatalogo.getString("email"),TipoUsuario.valueOf(rsCatalogo.getString("tipo_usuario")));
          
            resultado.add(usuarioActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCatalogo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    public void borrarUsuario(Usuario usuario){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=super.getConexion();

        try {
        stmUsuario=con.prepareStatement("delete from usuario where id_usuario = ?");
        stmUsuario.setString(1, usuario.getIdUsuario());
        stmUsuario.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public void nuevoUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){
        Connection con;
        PreparedStatement stmUsuario=null;
        con=this.getConexion();
        try  {
        stmUsuario=con.prepareStatement("Insert into Usuario Values(?, ?,?,?,?,?)");
        stmUsuario.setString(1, id);
        stmUsuario.setString(2, clave);
        stmUsuario.setString(3, nombre);
        stmUsuario.setString(4, direccion);
        stmUsuario.setString(5, email);
        stmUsuario.setString(6, tipo);
        stmUsuario.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage()+""+e.getErrorCode());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    
    public void actualizarUsuario(String id, String clave, String nombre, String direccion, String email, String tipo){     
        Connection con;
        PreparedStatement stmUsuario=null;
        
        con=super.getConexion();

        try {
        stmUsuario=con.prepareStatement("update usuario "+
                                    "set clave=?, "+
                                    "    nombre=?, "+
                                    "    direccion=?, "+
                                    "    email=?, "+
                                    "    tipo_usuario=? "+
                                    "where id_usuario=?");
        stmUsuario.setString(1, clave);
        stmUsuario.setString(2, nombre);
        stmUsuario.setString(3, direccion);
        stmUsuario.setString(4, email);
        stmUsuario.setString(5,tipo);
        stmUsuario.setString(6, id);
        stmUsuario.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage()+" "+ e.getErrorCode());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
          
        } 
    }
    
}
