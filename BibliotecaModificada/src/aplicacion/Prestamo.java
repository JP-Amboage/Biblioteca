/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Prestamo {
    private int libro;
    private int ejemplar;
    private String usuario;
    private String prestamo;
    private String vencimiento;
    
    public Prestamo (int libro, int ejemplar, String usuario, String prestamo, String vencimiento){
        this.libro=libro;
        this.ejemplar=ejemplar;
        this.usuario=usuario;
        this.prestamo = prestamo;
        this.vencimiento=vencimiento;
    }
    public int getEjemplar() {
        return ejemplar;
    }
    public int getLibro(){
        return this.libro;
    }
    public String getUsuario(){
       return this.usuario; 
    }    
    public String getPrestamo(){
        return this.prestamo;
    }
    public String getVencimiento(){
        return this.vencimiento;
    }
}
