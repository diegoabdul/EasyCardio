package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import model.Conexion;
import view.VentanaAddECG;

public class ControladorAddECG implements ActionListener {
	
	public static VentanaAddECG ventanaAddECG;
	public ControladorTecnicoGrafica ctg;
	Conexion datos= new Conexion("EasyCardio.db");
	public Vector <Integer> lol = new Vector <Integer>();	
	int contador;
	int tiempo;
	int frecuencia;
	String frecuencia1;
	String tiempo1;
	private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();

	
	public ControladorAddECG(VentanaAddECG ventanaAddECG, ControladorTecnicoGrafica _ctg) {
		super();
		ControladorAddECG.ventanaAddECG= ventanaAddECG;
		ctg = _ctg;
	}
	
	private SerialPortEventListener listener = new SerialPortEventListener() {
		@Override
	public void serialEvent(SerialPortEvent spe) {
		
			try {
				if (ino.isMessageAvailable()) {
					//jLabelOutput.setText("Resultado: " + ino.printMessage());
					// contador++;
					//   System.out.println(ino.printMessage());
					String s = ino.printMessage();
					Integer a = Integer.parseInt(s);
					lol.add(a);
					System.out.println(lol);
				}
			} catch (SerialPortException | ArduinoException ex) {
				Logger.getLogger(ControladorAddECG.class.getName()).log(Level.SEVERE, null, ex);
			}
			
//			try {
//				PrintWriter myFile = new PrintWriter("Thiriri.txt");
//				myFile.println(lol);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
	}};
	
	 public ControladorAddECG() {
		 
		//	initComponents();
			try {
				ino.arduinoRXTX("/dev/cu.usbmodem1421", 9600, listener);
			} catch (ArduinoException ex) {
				Logger.getLogger(ControladorAddECG.class.getName()).log(Level.SEVERE, null, ex);
			}
		}


		@SuppressWarnings("unchecked")
		// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frecuencia1=ventanaAddECG.textfieldfrecuencia.getText().toString();
		frecuencia=Integer.parseInt(frecuencia1);
		tiempo1=ventanaAddECG.textfieldtiempo.getText().toString();
		tiempo=Integer.parseInt(tiempo1);
		for(int i=0;i<frecuencia*tiempo;i++) {
			try {
				ino.sendData("1");
			} catch (ArduinoException | SerialPortException ex) {
				Logger.getLogger(ControladorAddECG.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

	}//GEN-LAST:event_jButton1ActionPerformed		
	}