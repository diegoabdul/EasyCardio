package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.AlgoritmosTPA;
import model.Conexion;
import view.VentanaLogin;
import view.VentanaMedico;
import view.VentanaMedicoGrafica;
import view.VentanaNuevoPaciente;
/**
 * Representacion de la ventana medico en la que se muestra la lista de sus pacientes y la 
 * impletancion de los metodos usados.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorMedico implements MouseListener, KeyListener,ActionListener {


	public int x;
	private VentanaMedico medico;
	public static String ruta;
	Conexion datos= new Conexion("EasyCardio.db");
	String NombreID=null;
	public static final String cerrar = "a";
	public static final String nuevo = "b";
	private ControladorMedico cm;
	public ControladorMedico(){

	}


	public ControladorMedico(VentanaMedico medico, MainController mc) {
		super();
		this.medico = medico;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount()==2 && !e.isConsumed()) {

			e.consume();
			String a = medico.list.getSelectedValue().toString();

			String[] campos=a.split(" ");

			VentanaMedicoGrafica vmg = new VentanaMedicoGrafica(campos[campos.length-1],datos);

			ControladorMedicoGrafica cmg = new ControladorMedicoGrafica(vmg);

			vmg.addController(cmg);



		}
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyPressed(KeyEvent arg0) { //BUSCADOR MEDICO
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!medico.textField.getText().toString().equals("")) {
				medico.modelo.clear();

				ResultSet rs =	datos.Buscador(medico.textField.getText().toString(),datos.MedicoID(medico.MedicoActual));
				Vector <String> PacienteMedico = new Vector<String>();
				Vector <String> PacienteMedico2 = new Vector<String>();
				try {
					while ( rs.next() ) {
						String prueba=null;
						String nombre=rs.getString("Nombre");
						String ID=rs.getString("ID");
						String DNI=rs.getString("DNI");
						prueba=nombre+" "+DNI+" "+ID;
						PacienteMedico.add(prueba);
					}
					PacienteMedico2=AlgoritmosTPA.bubble(PacienteMedico);

					rs.close();
					datos.stmt.close();
					datos.c.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] temporal1=PacienteMedico2.toArray(new String[PacienteMedico2.size()]);

				Vector<String> cadena=AlgoritmosTPA.Binaria(temporal1, medico.textField.getText().toString().toUpperCase());

				if(cadena.contains("NOCONSIGUIO")) {

					medico.modelo.addElement("NO SE HA ENCONTRADO, BORRE EL BUSCADOR Y PRESIONE ENTER");


				}
				if(!cadena.contains("NOCONSIGUIO")) {
					for(int i=0;i<cadena.size();i++) {
						medico.modelo.addElement(cadena.get(i));
						medico.list.setModel(medico.modelo);
					}
				}
			}
			if((medico.textField.getText().toString().equals(""))) {
				medico.modelo.clear();
				Vector <String> PacienteMedico3=datos.PacienteMedico(medico.MedicoActual);
				for(int x=0;x<PacienteMedico3.size();x++){
					medico.modelo.addElement(PacienteMedico3.get(x));
					medico.list.setModel(medico.modelo);
				}
			}
		}

	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Creo la ventana
		String accion =(String)e.getActionCommand();

		if ( accion.equals(cerrar)){


			VentanaLogin mainFrame = new VentanaLogin();

			// Creo el controlador pasando la ventana
			MainController mc = new MainController(mainFrame);

			// Le Asociamos el controlador a la ventana
			mainFrame.addController(mc);
			mainFrame.crearVista();			
			Runtime.getRuntime().gc();
			medico.dispose();
		} if ( accion.equals(nuevo)){
			VentanaNuevoPaciente vnp = new VentanaNuevoPaciente();
			ControladorNuevoPaciente cp = new ControladorNuevoPaciente(vnp,cm);
			vnp.addController(cp);

		}
	}
}