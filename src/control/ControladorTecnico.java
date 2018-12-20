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

import javax.swing.JOptionPane;

import model.AlgoritmosTPA;
import model.Conexion;
import view.VentanaAcceso;
import view.VentanaLogin;
import view.VentanaTecnico;
import view.VentanaTecnicoGrafica;

/**
 * Representacion de la ventana tecnico en la que se muestra la lista de los pacientes y la 
 * impletancion de los metodos usados.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorTecnico  implements MouseListener, KeyListener, ActionListener {

	VentanaAcceso vacc;
	private VentanaTecnico tecnico;
	public static String ruta;
	public String x;
	Conexion datos= new Conexion("EasyCardio.db");
	public static final String cerrarSesion = "a";
	public static final String aceptar = "b";
	public String seleccionlista=null;
	private int intentos=0;

	public ControladorTecnico(VentanaTecnico tecnico, MainController mc) {
		super();
		this.tecnico = tecnico;
	}

	@Override

	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount()==2 && !e.isConsumed()) {

			e.consume();
			seleccionlista = tecnico.list.getSelectedValue().toString();
			String[] campos=seleccionlista.split(" ");

			vacc = new VentanaAcceso();

			vacc.addController(this);
			vacc.crearVista();
			vacc.toFront();

			x=campos[campos.length-1];

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
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

			if(!tecnico.textField.getText().toString().equals("")) {
				tecnico.modelo.clear();
				Vector <String> PacienteTecnico = new Vector<String>();
				Vector <String> PacienteTecnico2 = new Vector<String>();
				ResultSet rs =	datos.BuscadorTecnico(tecnico.textField.getText().toString());
				try {
					while ( rs.next() ) {
						String prueba=null;
						String nombre=rs.getString("Nombre");
						String ID=rs.getString("ID");				
						prueba=nombre+" "+ID;
						PacienteTecnico.add(prueba);
					}

					PacienteTecnico2=AlgoritmosTPA.bubble(PacienteTecnico);


					rs.close();
					datos.stmt.close();
					datos.c.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] temporal1=PacienteTecnico2.toArray(new String[PacienteTecnico2.size()]);

				Vector<String> cadena=AlgoritmosTPA.Binaria(temporal1, tecnico.textField.getText().toString().toUpperCase());


				if(cadena.contains("NOCONSIGUIO")) {

					tecnico.modelo.addElement("NO SE HA ENCONTRADO, BORRE EL BUSCADOR Y PRESIONE ENTER");


				}
				if(!cadena.contains("NOCONSIGUIO")) {
					for(int i=0;i<cadena.size();i++) {
						tecnico.modelo.addElement(cadena.get(i));
						tecnico.list.setModel(tecnico.modelo);
					}

				}
			}

			if((tecnico.textField.getText().toString().equals(""))) {
				tecnico.modelo.clear();
				Vector <String> PacienteTecnico3=datos.PacienteTecnico();
				for(int x=0;x<PacienteTecnico3.size();x++){
					tecnico.modelo.addElement(PacienteTecnico3.get(x));

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
		int i = 0;
		String accion =(String)e.getActionCommand();

		if(accion.equals(cerrarSesion)){

			// Creo la ventana
			VentanaLogin mainFrame = new VentanaLogin();

			// Creo el controlador pasando la ventana
			MainController mc = new MainController(mainFrame);

			// Le Asociamos el controlador a la ventana
			mainFrame.addController(mc);
			mainFrame.crearVista();	
			Runtime.getRuntime().gc();
			tecnico.dispose();
		}

		if(accion.equals(aceptar)){
			boolean control = true;

			if(datos.VerificarDNI(x).equals(vacc.textField.getText())) {
				VentanaTecnicoGrafica vtg = new VentanaTecnicoGrafica(x);
				control =false;
				ControladorTecnicoGrafica ctg = new ControladorTecnicoGrafica(vtg);

				vtg.addController(ctg);

				vacc.dispose();
			}

			if((control)&&(intentos<3)) {
				JOptionPane.showMessageDialog(null,"DNI INCORRECTO INTENTE NUEVAMENTE"+" Al tercer intento se bloqueara el sistema ",accion, JOptionPane.ERROR_MESSAGE);
				intentos++;
				if(intentos==3) {
					JOptionPane.showMessageDialog(null,"SISTEMA BLOQUEADO ",accion, JOptionPane.ERROR_MESSAGE);
					vacc.dispose();
					tecnico.dispose();
				}
			}
		}

	}


}