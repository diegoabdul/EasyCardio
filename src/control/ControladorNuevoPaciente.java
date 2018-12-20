package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Conexion;
import model.ValidadorDNI;
import view.VentanaCambioDatos;
import view.VentanaCambioDatos2;
import view.VentanaGestion;
import view.VentanaMedico;
import view.VentanaNuevoPaciente;

public class ControladorNuevoPaciente implements ActionListener {
	
	
	//private MainController mc;
	private static VentanaNuevoPaciente ventanaNuevo;
	private ControladorMedico medico;
	Conexion datos= new Conexion("EasyCardio.db");
	public static final String modifDatos = "a";

	public static final String eliminar = "b";
	
	
	public ControladorNuevoPaciente(VentanaNuevoPaciente vnp , ControladorMedico _controladorMed) {
		
		ControladorNuevoPaciente.ventanaNuevo = vnp;
		medico = _controladorMed;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) { 
		boolean control=true;

		String validarPaciente = datos.ConsultaPacienteID(VentanaNuevoPaciente.lblPacientes.getText().toString());
		char [] comprobarNombre = VentanaNuevoPaciente.textField_1.getText().toCharArray();
		String Nombre=VentanaNuevoPaciente.textField_1.getText().toString();
		char [] comprobarSS = VentanaNuevoPaciente.textField_3.getText().toCharArray();
		String SS=VentanaNuevoPaciente.textField_3.getText().toString();
		String DNI=VentanaNuevoPaciente.textField_2.getText().toString();
		char [] letras = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','W','X','Y','Z','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] numeros = {'0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡'};
		char [] raro= {'!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
	
		if(SS.equals("")||DNI.equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR HAY CAMPOS VACIOS ");
			control=false;
		}
		ValidadorDNI dni = new ValidadorDNI(DNI);
		if(dni.validar()==false) {
			JOptionPane.showMessageDialog(null, "DNI INCORRECTO ");
			control=false;
		}
		
		int i=0;
		while(i<comprobarNombre.length) {
			int y=0;
			while(y<numeros.length) {
			if(comprobarNombre[i]==numeros[y]) {
				JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN EL NOMBRE ");
				control=false;
			}
			y++;
			}
			i++;
		}
		
		int z=0;
		while(z<comprobarSS.length) {
			int y=0;
			while(y<letras.length) {
			if(comprobarSS[z]==letras[y]) {
				JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN LA SEGURIDAD SOCIAL");
				control=false;
			}
			y++;
			}
			z++;
		}
		

		
		if(validarPaciente != null){
		
		JOptionPane.showMessageDialog(null, "ID ya existente , no se puede insertar el paciente. ");
		} 
		else if(control){
			JOptionPane.showMessageDialog(null, "Paciente Agregado ");
			datos.InsertarPaciente();
			ControladorNuevoPaciente.ventanaNuevo.dispose();
			
			
		}
		
		
		
	}


	
	
}