package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Conexion;
import view.VentanaEscribirMensaje;
import view.VentanaEscribirMensajeMedico;
import view.VentanaMedico;
import view.VentanaMedicoGrafica;
import view.VentanaVerMensaje;
import view.VentanaVerMensajeMedico;
/**
 * Representacion de la ventana medico grafica en la que se muestran a los pacientes de forma mas detallada, 
 * junto con su historial de ECG y la 
 * impletancion de los metodos usados.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorMedicoGrafica implements ActionListener, MouseListener  {
	
	private static VentanaMedicoGrafica vmg;
	//private MainController mc;
	public JFileChooser jfc;
	private VentanaMedico medico;
	public static String ruta;
	public int identificador;
	Conexion datos= new Conexion("EasyCardio.db");
	public ControladorECG controladorECG = new ControladorECG();
	public boolean seleccionado=false;
	private String comparar[] = new String[2];
	private int l=0;
	
	public static final String ayuda = "y";
	public static final String verComentarios = "b";
	public static final String escribirComentarios = "c";
	
	public ControladorMedicoGrafica(VentanaMedicoGrafica vmg ) {
		super();
		ControladorMedicoGrafica.vmg =vmg;
		//this.mc = mc;
	}

	
	public void actionPerformed(ActionEvent e) {
			
		String accion =(String)e.getActionCommand();
		
		if(accion.equals(escribirComentarios)){

			VentanaEscribirMensajeMedico ventanaMensajeMedico = new VentanaEscribirMensajeMedico();
			ControladorEscribirMensajeMedico cemm = new ControladorEscribirMensajeMedico(ventanaMensajeMedico, this);
			ventanaMensajeMedico.addController(cemm);
			ventanaMensajeMedico.crearVista();
		}
		if(accion.equals(verComentarios)){

			VentanaVerMensajeMedico ventanaVerMensajeMedico = new VentanaVerMensajeMedico();
			ControladorVerMensajeMedico cvmm = new ControladorVerMensajeMedico(ventanaVerMensajeMedico, this);
			ventanaVerMensajeMedico.addController(cvmm);
			ventanaVerMensajeMedico.crearVista();
		}
		
		if ( accion.equals(ayuda)){
		JOptionPane.showMessageDialog(null, "Para comparar graficas selecciona la opcion 'Comparar grafica' \nPrimero haz doble click en un ECG \nA continuacion haz doble click en el siguiente ECG con el que quieres comparar");
		
			seleccionado=true;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(vmg.subirGrafica.isSelected()) {
			
			int contador = 0;
			int ECG=0;
			boolean flag=true;
			if (e.getClickCount()==2 && (!e.isConsumed())) {
			flag=true;
			String a = vmg.lista.getSelectedValue().toString();
			
			
				comparar[l]=a;
				
			if(l==1) {
				e.consume();
				controladorECG.leeArchivo(comparar[0],comparar[1],"medico",identificador,2);
				l=0;
				flag=false;
			}
			if(flag) {
			l++;
			}
			}
			
		}
			 if(!vmg.subirGrafica.isSelected()) {
			if (e.getClickCount()==2 && !e.isConsumed()) {
			
			int contador2 = 0;
			int ECG2=0;
			e.consume();
			String b = vmg.lista.getSelectedValue().toString();

				for(int k=0;k<datos.ECGCount();k++) {
				String prueba = "ECG"+contador2;
				contador2++;	 
				if(prueba.matches(b)) {
					ECG2++;
					controladorECG.leeArchivo(prueba,"b","medico",identificador,1);
					}	
				}	
				contador2=0;	
		}
		}

	}
	


	@Override
	public void mouseEntered(MouseEvent e) {
	
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
	
	public void ECG(int id) {
		
		String ECG= datos.ECG(id);
		if(ECG==null) {
			vmg.modelo.addElement("NO HAY ECG ASOCIADO");
			vmg.lista.setModel(vmg.modelo);
			vmg.scrollPane.setViewportView(vmg.lista);
		}if(ECG!=null) {
		String[] campos=ECG.split(",");
		int contador = 0;
		identificador=id;
		for(int f=0;f<campos.length;f++) {
			for(int k=0;k<datos.ECGCount();k++) {
				String prueba = "ECG"+contador;
				
			if(prueba.matches(campos[f])) {
				vmg.modelo.addElement(campos[f]);
			}
			contador++;	 
		}	
		contador=0;	
		}
		
		vmg.lista.setModel(vmg.modelo);
		vmg.scrollPane.setViewportView(vmg.lista);
	}
	}


	
	

	

}
