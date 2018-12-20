package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorTecnicoGrafica;
import model.Conexion;
import model.Usuario;
/**
 * Representacion de la ventana tecnico grafica por medio del controlador tecnico grafica.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaTecnicoGrafica extends JFrame {

	private JPanel contentPane;
	private ControladorTecnicoGrafica ctg;
	public static JComboBox comboBox;
	private JButton subirGrafica;
	private JButton cargarECG;
	public static int i;
	public JMenuItem mntmNewMenuItem_escribirComentario;
	public JMenuItem mntmNewMenuItem_verComentario;
	public JLabel lista = new JLabel();
	public final DefaultListModel modelo = new DefaultListModel();
	public String y;
	Conexion datos= new Conexion("EasyCardio.db");
	Vector<String> asignar = datos.AsignarECG();
	public JScrollPane scrollPane = new JScrollPane();
	public JPanel imagen;
	public static int id;
	
	public VentanaTecnicoGrafica(String x) {
		y=x;
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2,  height/2);
	    setLocationRelativeTo(null);	
	    
	    setVisible(true);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(176, 224, 230));
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comentarios");
		mnNewMenu.setMnemonic(KeyEvent.VK_U);
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem_escribirComentario = new JMenuItem("Escribir comentario");
		
		mnNewMenu.add(mntmNewMenuItem_escribirComentario);
		
		mntmNewMenuItem_verComentario = new JMenuItem("Ver comentarios");
		
		mnNewMenu.add(mntmNewMenuItem_verComentario);
		

		subirGrafica = new JButton("Asignar ECG");
		cargarECG = new JButton("Agregar ECG al Sistema");
		comboBox = new JComboBox(asignar);
		
		
	
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDni = new JLabel("DNI :");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNSeguridadSocial = new JLabel("N\u00BA Seguridad Social : ");
		lblNSeguridadSocial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblEstadoDelPaciente = new JLabel("Estado del Paciente :");
		lblEstadoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		id=Integer.parseInt(x);
		i=id;
		JLabel lblNewLabel = new JLabel(datos.ConsultaPacienteNombre(id));
		
		JLabel lblAquiDni = new JLabel(datos.ConsultaPacienteDNI(id));
		
		JLabel lblAquiDireccion = new JLabel(datos.ConsultaPacienteDireccion(id));
		
		JLabel lblAquiSs = new JLabel(datos.ConsultaPacienteSS(id));
		
		JLabel lblAquiEstado = new JLabel(datos.ConsultaPacienteEstado(id));
		
		if((datos.ConsultaPacienteSexo(id)).matches("hombre")) {
			  imagen = new PaintPanel3();
		}
		if((datos.ConsultaPacienteSexo(id)).matches("mujer")) {
			 imagen = new PaintPanel4();
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(imagen, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
								.addGap(61))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(cargarECG, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(12)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNSeguridadSocial, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
									.addComponent(lblDni, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
									.addComponent(lblNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(lblEstadoDelPaciente, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
										.addGap(76))
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(lblDireccion, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
										.addGap(68)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAquiDni, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
										.addGap(77))
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAquiDireccion, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
										.addGap(76))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblAquiEstado, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
											.addComponent(lblAquiSs, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
										.addGap(74)))
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(subirGrafica, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(48))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(imagen, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
								.addGap(136))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 12, Short.MAX_VALUE)
									.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE))
								.addGap(26)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAquiDni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE))
								.addGap(26)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAquiDireccion, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDireccion))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(29)
										.addComponent(lblAquiSs, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(18)
										.addComponent(lblNSeguridadSocial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblEstadoDelPaciente)
									.addComponent(lblAquiEstado, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(86)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
									.addComponent(subirGrafica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cargarECG, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGap(19))
			);
			contentPane.setLayout(gl_contentPane);
			setResizable(false);


		}
	
	public void addController (ControladorTecnicoGrafica _ctg){
		ctg=_ctg;
		subirGrafica.addActionListener(ctg);
		subirGrafica.setActionCommand(ControladorTecnicoGrafica.ver);
		
		cargarECG.addActionListener(ctg);
		cargarECG.setActionCommand(ControladorTecnicoGrafica.addECG);
		
		mntmNewMenuItem_escribirComentario.addActionListener(ctg);
		mntmNewMenuItem_escribirComentario.setActionCommand(ControladorTecnicoGrafica.escribirComentarios);
		
		mntmNewMenuItem_verComentario.addActionListener(ctg);
		mntmNewMenuItem_verComentario.setActionCommand(ControladorTecnicoGrafica.verComentarios);

		
		
	}
	
	}
