package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

import control.ControladorMedicoGrafica;
import model.Conexion;


public class VentanaCompararEcg extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	public static float frecuencia;
	public static String respuesta;
	public String ECG;
	public int contadorECG;

	public VentanaCompararEcg(ArrayList<Float> listavalores, Float y, String ruta,String ultimo,String rol,String nombre,int contador,ArrayList<Float> listavalores2, Float y2, String ruta2,String ultimo2,String rol2,String nombre2) {
		crearVentana();
		ECG=ruta;
		contadorECG=contador;
		try {
			if (contador>=1) {
				nombre=nombre+" "+ruta;
				cargarGrafica(panel1, ruta,listavalores,y,nombre);
				panel1.setVisible(true);
			}
			if (contador>=2) {
				nombre2=nombre2+" "+ruta2;
					cargarGrafica(panel2, ruta2,listavalores2,y2,nombre2);
					panel2.setVisible(true);
					
			}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}

	
	

	/*
	 * Creamos la ventana
	 */
	public void crearVentana() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width, height);
	    setLocationRelativeTo(null);		
	    setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.setLayout(new BorderLayout(0, 0));

		panel2 = new JPanel();
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setLayout(new BorderLayout(0, 0));
		panel2.setVisible(false);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addGap(70))
		);
		panel1.setLayout(new BorderLayout(0, 0));
		panel2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Esperando 2º ECG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		panel2.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
	}

	private void cargarGrafica(JPanel panel, String ecg,ArrayList<Float> listavalores, Float y,String nombre) throws IOException {

		frecuencia=y;
		int i;
		XYSeries series = new XYSeries("Grafica");
		for(i=0;i<listavalores.size();i++) {
			series.add(y,listavalores.get(i));
			y=y+frecuencia;
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		panel.removeAll();
		JFreeChart chart = ChartFactory.createXYLineChart(nombre, "TIEMPO", "DATOS", dataset);
		ChartPanel chartpanel = new ChartPanel(chart);
		panel.add(chartpanel);
		panel.validate();
		customizarChart(chart);
	}
	
	private void customizarChart(JFreeChart grafica) {
		boolean control=true;
		XYPlot plot = grafica.getXYPlot();
		ValueAxis domainAxis = plot.getDomainAxis();
		domainAxis.setRange(0.0, 5.0);// Acotamos el eje horizontal para mejor visualizacion
		Conexion datos= new Conexion("EasyCardio.db");
		String informe = datos.ECGInforme(ECG);
		if((informe==null)&&(contadorECG==1)) {
			String ultimo="No hay informe anterior";
			respuesta = JOptionPane.showInputDialog("Informe anterior: "+ultimo);
			
			if(respuesta.equals("")||(respuesta.equals(null))) {
				control=false;
				JOptionPane.showMessageDialog(null, "DEBE ESCRIBIR ALGO EN EL INFORME");
				JOptionPane.showMessageDialog(null, "INFORME NO CAMBIADO");
			}
			
			if(control) {
			boolean flag = datos.InsertarInforme(respuesta, ECG);
			
			if(flag) {
			JOptionPane.showMessageDialog(null, "INFORME CORRECTAMENTE INSERTADO");
			}
			if(!flag) {
				JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR");
			}
		}
		}
		else if((informe!=null)&&(contadorECG==1)) {
			JOptionPane.showMessageDialog(null, informe);
		}
		
		
		try{
    		datos.c.close();
    	}catch (Exception e){
    		e.toString();
    	}
		
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		Shape diamond = ShapeUtilities.createDiamond((float) 0.4);
		renderer.setSeriesShape(0, diamond);
		renderer.setSeriesShapesVisible(0, false);

		// tooltip
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

		// Scroll con alt(option mac)
		plot.setDomainPannable(true);
		plot.setRangePannable(false);

		// Seleccionamos el render de las líneas
		plot.setRenderer(renderer);

		// fondo de la grafica
		plot.setBackgroundPaint(Color.WHITE);

	

		// Color líneas discontinuas del fondo

		// HORIZONTALES
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(new Color(139, 0, 0));
		// VERTICALES
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(new Color(139, 0, 0));

		plot.setDomainCrosshairPaint(Color.GREEN);
		plot.setRangeCrosshairPaint(Color.GREEN);

		// Grosor lineas de seleccion
		plot.setRangeCrosshairStroke(new BasicStroke(2.0f));
		plot.setDomainCrosshairStroke(new BasicStroke(2.0f));

		plot.setDomainCrosshairVisible(false);
		plot.setRangeCrosshairVisible(false);
	}
}
