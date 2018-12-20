package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Conexion;
import view.VentanaCambioDatos;
import view.VentanaCambioDatos2;
import view.VentanaGestion;

public class ControladorCambioDatos implements ActionListener {
	
	
	//private MainController mc;
	private static VentanaCambioDatos gestion2;
	private ControladorAdmin admin;
	Conexion datos= new Conexion("EasyCardio.db");
	public static final String modifDatos = "a";

	public static final String eliminar = "b";
	private ControladorCambioDatos cont2;

	
	public ControladorCambioDatos(VentanaCambioDatos cmbdatos , ControladorAdmin _controladorAdmin) {
		super();
		ControladorCambioDatos.gestion2 = cmbdatos;
		admin = _controladorAdmin;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String accion =(String)e.getActionCommand();
		
		if ( accion.equals(modifDatos)){
			
			VentanaCambioDatos2 vGestion2 = new VentanaCambioDatos2();
			ControladorCambioDatos2 cbd2 = new ControladorCambioDatos2 ( vGestion2, cont2);
			vGestion2.addController(cbd2);
			gestion2.dispose();
			 
		} if ( accion.equals(eliminar) ){
			
			datos.eliminarMedico(admin.a);
			datos.eliminarTecnico(admin.a);
			JOptionPane.showMessageDialog(null, "Se ha eliminado a " + admin.a);
			gestion2.dispose();
			
		} 
		
	}

	
	
}