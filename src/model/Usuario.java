package model;
/**
 * Procesos para los datos de la aplicacion.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class Usuario {

	public String nombre;
    public String password;
    public String rol;
    public String DNI;
    public String ID;
    public String relacion;
    public String seguridad;
    public String direccion;
    public String estado;
    public String sexo;
    public String ECG;
    
    
    public Usuario (){        
    }

    public Usuario(String usuario, String password) {
    	
        this.nombre = usuario;
        this.password = password;
        this.rol = rol;
        this.DNI = DNI;
        
    }

    public String getDNI() {
    	return DNI;
    }
    void setDNI() {
    	this.DNI = DNI;
    }
    public String getUsuario() {
        return nombre;
    }
    
    public String getPassword() {
        return password;
    }
    
    
    public String getRol() {
    	return rol;
    }

    
    
    void setUsuario ( String usuario){
    	
    	 this.nombre = usuario;
    }
    
   void setPassword( String password){
	
	   this.password = password;
   }
   
   void setRol(String rol) {
	   this.rol = rol;
   }
   

     
	
}
