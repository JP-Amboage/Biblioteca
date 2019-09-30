/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Prestamo;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumnogreibd
 */
public class DAOprestamos extends AbstractDAO {
    
    public DAOprestamos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    public void prestar(String usuario, int libro, int ejemplar){
        Connection con;
        PreparedStatement stmPrestamo=null;    
        con=super.getConexion();
        
        try {
        stmPrestamo=con.prepareStatement("insert into prestamo(usuario, libro, ejemplar, fecha_prestamo, fecha_devolucion) "+
                                      "values (?,?,?,current_date,null)");
        stmPrestamo.setString(1, usuario);
        stmPrestamo.setInt(2, libro);
        stmPrestamo.setInt(3, ejemplar);
        stmPrestamo.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public void devolver(int libro, int ejemplar){
        Connection con;
        PreparedStatement stmLibro=null;
        PreparedStatement stmAutores=null;
        PreparedStatement stmIdLibro=null;
        ResultSet rsIdLibro;
        Integer numAutor;
        Integer idLibro=null;

        con=super.getConexion();
        
        try {
        stmLibro=con.prepareStatement("UPDATE prestamo SET fecha_devolucion = current_date WHERE libro = ? AND ejemplar = ? AND fecha_devolucion isnull");
        stmLibro.setInt(1, libro);
        stmLibro.setInt(2, ejemplar);
        stmLibro.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmLibro.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public HashMap<String,Integer> vencidos(){
        HashMap<String,Integer>  resultado = new HashMap<String,Integer>();
        Connection con;
        ResultSet rsVencidos=null;
        PreparedStatement stmPrestamos=null;
        
        con=this.getConexion();
        
        String consulta = "select usuario, count (usuario) as num " +
                          "from prestamo " +
                          "where fecha_devolucion isnull AND current_date-fecha_prestamo>30 " +
                          "group by usuario" ;
        try  {
         stmPrestamos=con.prepareStatement(consulta);       
         rsVencidos=stmPrestamos.executeQuery();        
        while (rsVencidos.next()){           
            resultado.put(rsVencidos.getString("usuario"),rsVencidos.getInt("num"));
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public boolean estaPrestado(int libro, int ejemplar){
        boolean  resultado=false;
        Connection con;
        ResultSet rsPrestado=null;
        PreparedStatement stmPrestamos=null;
        
        con=this.getConexion();
        
        String consulta = "select * from prestamo " +
                          "where libro = ? AND ejemplar=? AND fecha_devolucion isnull";
        try  {
         stmPrestamos=con.prepareStatement(consulta);
         stmPrestamos.setInt(1,libro);
         stmPrestamos.setInt(2,ejemplar);
         rsPrestado=stmPrestamos.executeQuery();        
        while (rsPrestado.next()){           
            resultado=true;
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public HashMap<Integer,Prestamo> prestado(Integer libro){
        HashMap<Integer,Prestamo>  resultado = new HashMap<Integer,Prestamo>();
        Connection con;
        ResultSet rsVencidos=null;
        PreparedStatement stmPrestamos=null;
        
        con=this.getConexion();
        
        String consulta = "select libro, ejemplar, usuario,  fecha_prestamo, fecha_prestamo +30 as vencimiento " +
                          "from prestamo " +
                          "where fecha_devolucion isnull AND libro = ? ";
        try  {
         stmPrestamos=con.prepareStatement(consulta); 
         stmPrestamos.setInt(1,libro);
         rsVencidos=stmPrestamos.executeQuery();        
        while (rsVencidos.next()){
            Prestamo p = new Prestamo(rsVencidos.getInt("libro"),
                    rsVencidos.getInt("ejemplar"),
                    rsVencidos.getString("usuario"),
                    rsVencidos.getString("fecha_prestamo"),
                    rsVencidos.getString("vencimiento"));
            resultado.put(rsVencidos.getInt("ejemplar"),p);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public boolean existe(int libro, int ejemplar){
        boolean  resultado=false;
        Connection con;
        ResultSet rsPrestado=null;
        PreparedStatement stmPrestamos=null;
        
        con=this.getConexion();
        
        String consulta = "select * from ejemplar " +
                          "where libro = ? AND num_ejemplar=?";
        try  {
         stmPrestamos=con.prepareStatement(consulta);
         stmPrestamos.setInt(1,libro);
         stmPrestamos.setInt(2,ejemplar);
         rsPrestado=stmPrestamos.executeQuery();        
        while (rsPrestado.next()){           
            resultado=true;
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmPrestamos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public void guardarBusqueda(ArrayList<Usuario> usuarios, HashMap<String,Integer> vencidos){  
        Connection con;
        PreparedStatement stmPrestamo=null;    
        con=super.getConexion();
        for(Usuario u: usuarios){
            int pendientes=0;
            if(vencidos.containsKey(u.getIdUsuario()))pendientes=vencidos.get(u.getIdUsuario());
            try {
            stmPrestamo=con.prepareStatement("insert into usuarios_buscados(usuario, prestamos_vencidos) values (?,?)");
            stmPrestamo.setString(1, u.getIdUsuario());
            stmPrestamo.setInt(2, pendientes);
            stmPrestamo.executeUpdate();
            } catch (SQLException e){
              System.out.println(e.getMessage());  
            }finally{
              try {stmPrestamo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
    }
    
}
