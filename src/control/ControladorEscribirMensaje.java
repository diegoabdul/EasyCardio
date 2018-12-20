package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import model.Conexion;
import view.VentanaCambioDatos;
import view.VentanaEscribirMensaje;
import view.VentanaLogin;
import view.VentanaTecnico;
import view.VentanaTecnicoGrafica;


public class ControladorEscribirMensaje implements ActionListener {

	
	public static VentanaEscribirMensaje ventanaMensaje;
	public ControladorTecnicoGrafica ctg;
	Conexion datos= new Conexion("EasyCardio.db");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	public ControladorEscribirMensaje(VentanaEscribirMensaje vem, ControladorTecnicoGrafica _ctg) {
		super();
		ControladorEscribirMensaje.ventanaMensaje = vem;
		ctg = _ctg;
	}
	public void actionPerformed(ActionEvent e) {
	boolean control=datos.EscribirMensajeTecnico(sdf.format(ventanaMensaje.timestamp).toString(),datos.ConsultaPacienteNombre(VentanaTecnicoGrafica.i).toString(), ventanaMensaje.editorPane.getText().toString(), VentanaTecnico.nombre.toString());
		if(control) {
		JOptionPane.showMessageDialog(null, "Mensaje enviado satisfactoriamente");
		ventanaMensaje.dispose();
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
