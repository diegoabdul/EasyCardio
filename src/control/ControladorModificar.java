
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Conexion;
import model.ValidadorDNI;
import view.VentanaGestion;
import view.VentanaLogin;
import view.VentanaModificar;
import view.VentanaNuevoPaciente;
/**
 * Representacion de la ventana modificar en la que se muestran los campos a rellenar para aï¿½adir un tecnico o medico y la 
 * impletancion de los metodos usados.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorModificar implements ActionListener {
	
	//private MainController mc;
	private static VentanaGestion gestion;
	private ControladorAdmin controladorAdmin;
	Conexion datos= new Conexion("EasyCardio.db");
	
	public ControladorModificar(VentanaGestion gestionar , ControladorAdmin _controladorAdmin) {
		super();
		ControladorModificar.gestion = gestionar;
		controladorAdmin = _controladorAdmin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean control=true;
		Object selected = gestion.comboBox.getSelectedItem();
		char [] letras = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','W','X','Y','Z','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] numeros = {'0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡'};
		char [] raro= {'!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] numeros2 = {'0','1','2','3','4','5','6','7','8','9','!','"','#','$','%','&','/','(',')','=','?','q','¿','@','|','°','´','+','-','{','.',',','}',']','[','*',':',';','<','>','¡',' '};
		char [] comprobarNombre = VentanaGestion.Nombre.getText().toCharArray();
		char [] comprobarNombreID = VentanaGestion.NombreId.getText().toCharArray();
		char [] comprobarID = VentanaGestion.ID.getText().toCharArray();
		String DNI=VentanaGestion.DNI.getText().toString();
		String NombreID=VentanaGestion.NombreId.getText().toString();
		
		if(DNI.equals("")||NombreID.equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR HAY CAMPOS VACIOS ");
			control=false;
		}
		
		 ValidadorDNI dni = new ValidadorDNI(DNI);
			if(dni.validar()==false) {
				JOptionPane.showMessageDialog(null, "DNI INCORRECTO ");
				control=false;
			}
			
			int t=0;
			while(t<comprobarNombreID.length) {
				int y=0;
				while(y<numeros.length) {
				if(comprobarNombreID[t]==numeros2[y]) {
					JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN EL NOMBREID ");
					control=false;
				}
				y++;
				}
				t++;
			}
			
			int z=0;
			while(z<comprobarID.length) {
				int y=0;
				while(y<letras.length) {
				if(comprobarID[z]==letras[y]) {
					JOptionPane.showMessageDialog(null, "ERROR HAY CARACTERES NO ADMITIDOS EN EL ID");
					control=false;
				}
				y++;
				}
				z++;
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
		
		if ( selected.equals("Medico")){
			
			
			
			 String validarMedico=datos.ConsultaMedicoID(VentanaGestion.ID.getText().toString());
			 if(validarMedico != null){
				 JOptionPane.showMessageDialog(null, "ID ya existente , no se puede insertar el medico. ");
			
			
			 } else {
				 
				
					
					if(control){
				 Boolean flag = datos.InsertarMedico();
					Boolean flag2= datos.medico;
					if(flag&&!flag2) {
						JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE su clave sera su DNI con letra");
						gestion.dispose();
						try{
				    		datos.c.close();
				    	}catch (Exception p){
				    		p.toString();
				    	}
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Error al agregar intente nuevamente");
					try{
			    		datos.c.close();
			    	}catch (Exception p){
			    		p.toString();
			    	}
			 }
		} 
	
		if ( selected.equals("Tecnico")){
			
			String validarTecnico=datos.ConsultaTecnicoID(VentanaGestion.ID.getText().toString());
			
			if(validarTecnico != null){
				JOptionPane.showMessageDialog(null, "ID ya existente , no se puede insertar el tecnico. ");
			
			
			}else{
				if(control){
				Boolean flag = datos.InsertarTecnico();
				Boolean flag2 = datos.tecnico;
				if(flag&&!flag2) {
					JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE su clave sera su DNI con letra");
					gestion.dispose();
					try{
			    		datos.c.close();
			    	}catch (Exception p){
			    		p.toString();
			    	}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Error al agregar intente nuevamente");
				try{
		    		datos.c.close();
		    	}catch (Exception p){
		    		p.toString();
		    	}
			}
		} 			
		
		
		
		try{
    		datos.c.close();
    	}catch (Exception p){
    		p.toString();
    	}
		
	}

}
