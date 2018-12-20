package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Conexion;
import view.VentanaAdmin;
import view.VentanaCambioDatos;
import view.VentanaGestion;
import view.VentanaLogin;
import view.VentanaModificar;

/**
 * Representacion de la ventana admin en la cual se muestra una lista con todos los tecnicos y medicos
 * del sistema y la implementacion de los metodos usados para su funcionamiento.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */

public class ControladorAdmin implements MouseListener, ActionListener {
	
	private static VentanaAdmin admin;
	private MainController mc;
	
	public static final String modificar = "a";
	public static final String verMedico = "b";
	public static final String verTecnico = "c";
	public static final String Medico = "d";
	public static final String Tecnico = "e";
	public static final String verAmbos = "r";
	public static final String cerrarSesion = "z";
	public static String a;
	Conexion datos= new Conexion("EasyCardio.db");
	
	public static String accion ;

	
	boolean control =true;

	
	public ControladorAdmin(VentanaAdmin admin, MainController mc) {
		super();
		this.admin =admin;
		this.mc = mc;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
			if (e.getClickCount()==2 && !e.isConsumed()) {
			
			e.consume();
			a = admin.list.getSelectedValue().toString();
			
			
			 VentanaCambioDatos vCambio = new VentanaCambioDatos();
				
			 ControladorCambioDatos cCambio = new ControladorCambioDatos(vCambio,this);
			 vCambio.addController(cCambio);
					
		
			 
		}
				
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent f) {
		
	
		 accion =(String)f.getActionCommand();
		
		if ( accion.equals(modificar)){
			
			 VentanaGestion vGestionar = new VentanaGestion();
			
			 ControladorModificar cModificar = new ControladorModificar(vGestionar,this);
			 vGestionar.addController(cModificar);
			 
		}
		if ( accion.equals(cerrarSesion)){

			// Creo la ventana
			VentanaLogin mainFrame = new VentanaLogin();

			// Creo el controlador pasando la ventana
			MainController mc = new MainController(mainFrame);

			// Le Asociamos el controlador a la ventana
			mainFrame.addController(mc);
			mainFrame.crearVista();	
			Runtime.getRuntime().gc();
			admin.dispose();
			
		}
		if(accion.equals(Medico)) {
			
			admin.modelo.clear();
			
		
			for(int i=0;i< datos.ConsultaMedicosAdmin().size();i++){
				admin.modelo.addElement(datos.ConsultaMedicosAdmin().elementAt(i));
			}

		}
		
		if(accion.equals(Tecnico)) {
			admin.modelo.clear();
			
			
			for(int i=0;i< datos.ConsultaTecnicosAdmin().size();i++){
				admin.modelo.addElement(datos.ConsultaTecnicosAdmin().elementAt(i));
			}


		}
		if (accion.equals(verAmbos)){
			admin.modelo.clear();
			
			for(int i=0;i< datos.ConsultaMedicosAdmin().size();i++){
				admin.modelo.addElement(datos.ConsultaMedicosAdmin().elementAt(i));
			}
			
			for(int i=0;i< datos.ConsultaTecnicosAdmin().size();i++){
				admin.modelo.addElement(datos.ConsultaTecnicosAdmin().elementAt(i));
			}

		}
		
		try{
    		datos.c.close();
    	}catch (Exception e){
    		e.toString();
    	}
	}
		
}