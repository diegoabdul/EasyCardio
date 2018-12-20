package control;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Conexion;
import model.Usuario;
import view.VentanaAdmin;
import view.VentanaLogin;
import view.VentanaMedico;
import view.VentanaTecnico;
/**
 * Inicio de la aplicacion e implementacion de los metodos principales.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class MainController implements ActionListener{
	//public JFrame ventanaControlada;
	VentanaLogin ventanaControlada;
	private static String nombre;
	//public static final String SEPARADOR = ";";
	public static final String add = "a";
	public static Vector <Usuario> listaUsuarios = new Vector();
	private int intentos=0;
	public final static String BOTON="L";
	Conexion datos= new Conexion("EasyCardio.db");
	
	public MainController(VentanaLogin win){
		ventanaControlada=win;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		// Cambio el cursor por un relog
		Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
        ventanaControlada.setCursor(cur);        

    	String cmd = (String)(e.getActionCommand());
        abrirVentana(cmd);
		
		// Dejo el cursor como estaba
		cur = new Cursor(Cursor.DEFAULT_CURSOR);
        ventanaControlada.setCursor(cur);  
        
        if (cmd.equals(add)){
        	JOptionPane.showMessageDialog(null, "hola");
    		
        }      
    } 
	
    public void abrirVentana(String cmd){	
    	boolean control = true;
    	if (cmd.equals(MainController.BOTON)) { 
 
					 
					 
					 if(datos.LoginAdmin(VentanaLogin.textField_1.getText().toLowerCase().toString(),VentanaLogin.passwordField_1.getText().toLowerCase().toString())) {
						 JOptionPane.showMessageDialog(null, "Usuario registrado:  " +  VentanaLogin.textField_1.getText() + " Puede Acceder " + "TIENE ROL DE: "+"ADMINISTRADOR");
						 boolean control2 = true;
						 // Creo la ventana
						VentanaAdmin va = new VentanaAdmin();
						// Creo el controlador pasando la ventana
						ControladorAdmin ca = new ControladorAdmin(va, this);			
						// Le Asociamos el controlador a la ventana
						va.addController(ca);
						control = false;
						 va.VentanaAdmin(control2);
						ventanaControlada.dispose();
					 }
					 if(datos.LoginTecnico(VentanaLogin.textField_1.getText().toLowerCase().toString(),VentanaLogin.passwordField_1.getText().toLowerCase().toString())) {
						 JOptionPane.showMessageDialog(null, "Usuario registrado:  " +  VentanaLogin.textField_1.getText() + " Puede Acceder "+ "TIENE ROL DE: TECNICO");
						 String nombre=VentanaLogin.textField_1.getText();
						 boolean control2 = true;
						 VentanaTecnico vt = new VentanaTecnico();
						 ControladorTecnico ct = new ControladorTecnico( vt ,this);
						 vt.addController(ct);
						 vt.VentanaTecnico(control2,nombre);
						 control = false;
						 ventanaControlada.dispose();
					 }

					 if(datos.LoginMedico(VentanaLogin.textField_1.getText().toLowerCase().toString(),VentanaLogin.passwordField_1.getText().toLowerCase().toString())) {
						 JOptionPane.showMessageDialog(null, "Usuario registrado:  " +  VentanaLogin.textField_1.getText() + " Puede Acceder "+ "TIENE ROL DE: MEDICO");
						 boolean control2 = true;
						 VentanaMedico vm = new VentanaMedico();
						 ControladorMedico cm = new ControladorMedico( vm ,this);
						 vm.addController(cm);
						 control = false;
						 vm.VentanaTecnico(VentanaLogin.textField_1.getText().toLowerCase().toString(),datos,control2);
						 ventanaControlada.dispose();
					 }
					 if((control)&&(intentos<3)) {
							JOptionPane.showMessageDialog(null,"USUARIO INCORRECTO"+" Al tercer intento se bloqueara el sistema ",cmd, JOptionPane.ERROR_MESSAGE);
							intentos++;
							if(intentos==3) {
								JOptionPane.showMessageDialog(null,"SISTEMA BLOQUEADO ",cmd, JOptionPane.ERROR_MESSAGE);
								ventanaControlada.dispose();
							}
						} 
			 }
    	
    	try{
    		datos.c.close();
    	}catch (Exception e){
    		e.toString();
    	}
    	
    }
    public static void main(String[] args) {
		// CARGAR EL NUEVO LOOK AND FEEL SI SE PUEDE
		// RECOGEMOS TODOS LOS LOOK DISPONIBLES EN LA VERSION DE JDK
    	
   
    	
		LookAndFeelInfo tabla_laf[] = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo objeto_aparicencia : tabla_laf) {
			// COMPROBAMOS SI EXISTE NIMBUS
			if (objeto_aparicencia.getName().equals("Nimbus")) {
				// CARGAMOS NIMBUS CUANDO EXISTA SEGUN LA VERSION DE JDK
				// USADA
				try {
					UIManager.setLookAndFeel(objeto_aparicencia.getClassName());
					System.out.println("Cargando nimbus......");
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(ControladorAddECG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(ControladorAddECG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(ControladorAddECG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				} catch (javax.swing.UnsupportedLookAndFeelException ex) {
					java.util.logging.Logger.getLogger(ControladorAddECG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
				}
				//</editor-fold>

				/* Create and display the form */
				;}
			}
		// Creo la ventana
				VentanaLogin mainFrame = new VentanaLogin();

				// Creo el controlador pasando la ventana
				MainController mc = new MainController(mainFrame);

				// Le Asociamos el controlador a la ventana
				mainFrame.addController(mc);
				mainFrame.crearVista();	
		}
}


	
