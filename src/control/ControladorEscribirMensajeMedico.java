package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import model.Conexion;
import view.VentanaEscribirMensaje;
import view.VentanaEscribirMensajeMedico;
import view.VentanaLogin;
import view.VentanaMedico;
import view.VentanaMedicoGrafica;
import view.VentanaTecnico;
import view.VentanaTecnicoGrafica;

public class ControladorEscribirMensajeMedico implements ActionListener{

	public static VentanaEscribirMensajeMedico ventanaMensajeMedico;
	public ControladorMedicoGrafica cmg;
	Conexion datos= new Conexion("EasyCardio.db");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	public ControladorEscribirMensajeMedico(VentanaEscribirMensajeMedico vemm, ControladorMedicoGrafica _cmg) {
		super();
		ControladorEscribirMensajeMedico.ventanaMensajeMedico = vemm;
		cmg = _cmg;
	}
	public void actionPerformed(ActionEvent e) {
	boolean control=datos.EscribirMensajeMedico(sdf.format(ventanaMensajeMedico.timestamp).toString(),datos.ConsultaPacienteNombre(VentanaMedicoGrafica.id).toString(), ventanaMensajeMedico.editorPane.getText().toString(),VentanaLogin.textField_1.getText().toLowerCase().toString());
		if(control) {
		JOptionPane.showMessageDialog(null, "Mensaje enviado satisfactoriamente");
		ventanaMensajeMedico.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
		}
		try{
    		datos.c.close();
    	}catch (Exception t){
    		t.toString();
    	}
	}
}
