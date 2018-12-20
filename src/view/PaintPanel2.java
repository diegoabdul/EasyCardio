package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.lang.Object;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import control.MainController;
import model.Usuario;
/**
 * Metodo para pintar la grafica con los datos de los ECG y su representacion en un frame aparte.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class PaintPanel2 extends JPanel {
	public PaintPanel2() {
	}

	private JPanel contentPane;
	JFreeChart Grafica;
	public static float frecuencia;
	public static String respuesta;
	
	public static void paint2(ArrayList<Float> listavalores, Float y, String ruta,String ultimo,String rol,String nombre) {
		frecuencia=y;
		int i;
		XYSeries series = new XYSeries("Grafica");
		for(i=0;i<listavalores.size();i++) {
			series.add(y,listavalores.get(i));
			y=y+frecuencia;
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		JFreeChart chart = ChartFactory.createXYLineChart(nombre, "TIEMPO", "DATOS", dataset);
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.WHITE);
		ChartFrame frame = new ChartFrame("ELECTROCARDIOGRAMA",chart);
		frame.setSize(width, height/2);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		XYPlot xyPlot = chart.getXYPlot();
		ValueAxis domainAxis = xyPlot.getDomainAxis();
		ValueAxis rangeAxis = xyPlot.getRangeAxis();
		
		xyPlot.setDomainPannable(true);
		xyPlot.setRangePannable(false);
		xyPlot.setBackgroundPaint(Color.white);
		domainAxis.setRange(0.0, 15.0);
		if((ultimo==null)&&(rol.equals("medico"))) {
			ultimo="No hay informe anterior";
			respuesta = JOptionPane.showInputDialog("Informe anterior: "+ultimo);
		}
		else if((rol.equals("medico"))&&(ultimo!=null)) {
			 JOptionPane.showMessageDialog(null, ultimo);
		}		
	}


	

	


}
