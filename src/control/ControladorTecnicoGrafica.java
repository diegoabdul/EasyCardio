package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Conexion;
import model.Usuario;
import view.PaintPanel3;
import view.PaintPanel4;
import view.VentanaAddECG;
import view.VentanaEscribirMensaje;
import view.VentanaTecnico;
import view.VentanaTecnicoGrafica;
import view.VentanaVerMensaje;

/**
 * Representacion de la ventana tecnico grafica para los datos del paciente
 * e implementacion de los metodos usados en la ventana.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorTecnicoGrafica implements ActionListener, MouseListener {
	
	private static VentanaTecnicoGrafica vtg;
	//private MainController mc;
	public JFileChooser jfc;
	private MainController mc;

	private VentanaTecnico tecnico;
	public static String ruta;
	public static final String ver = "a";
	public static final String verComentarios = "b";
	Conexion datos= new Conexion("EasyCardio.db");

	public static final String escribirComentarios = "c";
	public static final String addECG = "d";

	public ControladorECG controladorECG = new ControladorECG();
	public boolean control = false;
	
	public ControladorTecnicoGrafica(VentanaTecnicoGrafica vtg) {
		super();
		ControladorTecnicoGrafica.vtg =vtg;
		this.mc = mc;
	}

	
	public void actionPerformed(ActionEvent e) {
		
		String accion =(String)e.getActionCommand();
		
		if(accion.equals(addECG)){

			VentanaAddECG ventanaAddECG = new VentanaAddECG();
			ControladorAddECG caECG = new ControladorAddECG(ventanaAddECG,this);
			ventanaAddECG.addController(caECG);
			ventanaAddECG.crearVista();
		}
		if(accion.equals(escribirComentarios)){
			
			VentanaEscribirMensaje ventanaMensaje = new VentanaEscribirMensaje();
			ControladorEscribirMensaje cem = new ControladorEscribirMensaje(ventanaMensaje, this);
			ventanaMensaje.addController(cem);
			ventanaMensaje.crearVista();
		}
		if(accion.equals(verComentarios)){

			VentanaVerMensaje ventanaVerMensaje = new VentanaVerMensaje();
			ControladorVerMensaje cvm = new ControladorVerMensaje(ventanaVerMensaje, this);
			ventanaVerMensaje.addController(cvm);
			ventanaVerMensaje.crearVista();
		}

		if(accion.equals(ver)) {
			String ECG=ControladorTecnicoGrafica.vtg.comboBox.getSelectedItem().toString();
			JOptionPane.showMessageDialog(null," Asignando "+ECG+" Al Paciente: "+ControladorTecnicoGrafica.vtg.id);
			String ECGExistente=datos.ECGExistente(ControladorTecnicoGrafica.vtg.id);
			if(ECGExistente.contains(ECG)) {
				JOptionPane.showMessageDialog(null,"ERROR ECG YA EXISTENTE");
			}
			else {
			String ECGNuevo=ECGExistente+","+ECG;
			boolean flag=datos.InsertarECG(ECGNuevo, ControladorTecnicoGrafica.vtg.id);
			if(flag==true) {
				JOptionPane.showMessageDialog(null,"ECG Asignado Correctamente");
			}
			if(flag==false) {
				JOptionPane.showMessageDialog(null,"Error");
			}
			}
			try{
				datos.c.close();
			}catch (Exception j){
				j.toString();
			}

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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

	

}
