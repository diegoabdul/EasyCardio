package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Conexion;
import model.Usuario;
import view.PaintPanel2;
import view.VentanaCompararEcg;
/**
 * Representacion del paint panel 2 en el cual se lee el fichero con los datos del ECG y se representa en 
 * el paint panel 2.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class ControladorECG  {
	
	public  String y;
	final  String SEPARADOR = ";";
	public ArrayList<Float> listavalores;
	public ArrayList<Float> listavalores2;
	
	Conexion datos= new Conexion("EasyCardio.db");
	
		public void leeArchivo (String ruta,String ruta2,String rol,int identificador,int contador) {
			if(ruta2.equals("b")) {
				
		String informe = datos.ECGInforme(ruta);
		 listavalores = new ArrayList<Float>();
		 String ECG = datos.ECGPuntos(ruta);
		 float frecuencia = datos.ECGFrecuencia(ruta);
		 String nombre= datos.ConsultaPacienteNombre(identificador);
		 String DNI=datos.ConsultaPacienteDNI(identificador);
		 String nombreECG=nombre+" "+DNI;
			   StringTokenizer st = new StringTokenizer(ECG, SEPARADOR);

			   while(st.hasMoreTokens()&&(ECG!=null)) {
				  
				   float x = Float.parseFloat(st.nextToken());
				   listavalores.add(x);
			   }
			   VentanaCompararEcg ecg = new VentanaCompararEcg(listavalores, (1/frecuencia),ruta,informe,rol,nombreECG,contador,listavalores,(1/frecuencia),ruta,informe,rol,nombreECG);
			   ecg.setVisible(true);
			   
			}else if(!ruta2.equals("b")) {
				String informe = datos.ECGInforme(ruta);
				 listavalores = new ArrayList<Float>();
				 String ECG = datos.ECGPuntos(ruta);
				 float frecuencia = datos.ECGFrecuencia(ruta);
				 String nombre= datos.ConsultaPacienteNombre(identificador);
				 String DNI=datos.ConsultaPacienteDNI(identificador);
				 String nombreECG=nombre+" "+DNI;
					   StringTokenizer st = new StringTokenizer(ECG, SEPARADOR);

					   while(st.hasMoreTokens()&&(ECG!=null)) {
						  
						   float x = Float.parseFloat(st.nextToken());
						   listavalores.add(x);
					   }
					   
					   
					   String informe2 = datos.ECGInforme(ruta2);
						 listavalores2 = new ArrayList<Float>();
						 String ECG2 = datos.ECGPuntos(ruta2);
						 float frecuencia2 = datos.ECGFrecuencia(ruta2);

						StringTokenizer st2 = new StringTokenizer(ECG2, SEPARADOR);

							   while(st2.hasMoreTokens()&&(ECG2!=null)) {
								  
								   float x = Float.parseFloat(st2.nextToken());
								   listavalores2.add(x);
							   }
VentanaCompararEcg ecg = new VentanaCompararEcg(listavalores, (1/frecuencia),ruta,informe,rol,nombreECG,contador,listavalores2, (1/frecuencia2),ruta2,informe2,rol,nombreECG);
ecg.setVisible(true);
			}

		  }

}
