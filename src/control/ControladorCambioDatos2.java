package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Conexion;
import model.ValidadorDNI;
import view.VentanaCambioDatos;
import view.VentanaCambioDatos2;
import view.VentanaGestion;
import view.VentanaLogin;
import view.VentanaNuevoPaciente;

public class ControladorCambioDatos2 implements ActionListener {
	
	
	//private MainController mc;
	private static VentanaCambioDatos2 vcmb2;
	private ControladorCambioDatos cont2;
	
	Conexion datos= new Conexion("EasyCardio.db");
	public static final String modifDatos2 = "a";
	public static final String eliminar = "b";
	public static final String Medico = "d";
	public static final String Tecnico = "e";

	
	public ControladorCambioDatos2(VentanaCambioDatos2 cmbdatos2 , ControladorCambioDatos _controladorAdmin) {
		super();
		ControladorCambioDatos2.vcmb2 = cmbdatos2;
		cont2 = _controladorAdmin;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) { // No lee evento
		boolean control=true;
		char [] comprobarNombre = VentanaCambioDatos2.textField_Nombre.getText().toCharArray();
		String DNI=VentanaCambioDatos2.textField_DNI.getText().toString();
		char [] comprobarID = VentanaCambioDatos2.ID.getText().toCharArray();
		char [] comprobarNombreID = VentanaCambioDatos2.textField_NombreID.getText().toCharArray();
		char [] letras = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','W','X','Y','Z','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] numeros = {'0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡'};
		char [] raro= {'!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] numeros2 = {'0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};

		
		
		
		String accion =(String)e.getActionCommand();
		
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
		
		int p=0;
		while(p<comprobarNombreID.length) {
			int y=0;
			while(y<numeros.length) {
			if(comprobarNombreID[p]==numeros2[y]) {
				JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN EL NOMBREID ");
				control=false;
			}
			y++;
			}
			p++;
		}
		
		int z=0;
		while(z<comprobarID.length) {
			int y=0;
			while(y<letras.length) {
			if(comprobarID[z]==letras[y]) {
				JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN El ID");
				control=false;
			}
			y++;
			}
			z++;
		}
		
		if ( ControladorAdmin.accion.equals(Medico)&&(control==true)){
			
			
			
			datos.ModificarMedico(ControladorAdmin.a);
		JOptionPane.showMessageDialog(null, " Datos del medico " + ControladorAdmin.a + " modificados con exito.");
		ControladorCambioDatos2.vcmb2.dispose();
			
		} else if(ControladorAdmin.accion.equals(Tecnico)&&(control==true)) {
			datos.ModificarTecnico(ControladorAdmin.a);
			
			JOptionPane.showMessageDialog(null, " Datos del tecnico " + ControladorAdmin.a + " modificados con exito.");
			ControladorCambioDatos2.vcmb2.dispose();
		}
		
		
		
		
	}

	
	
}