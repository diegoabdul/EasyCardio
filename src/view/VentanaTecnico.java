package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControladorTecnico;
import model.Conexion;
import model.Usuario;
/**
 * Representacion de la ventana tecnico por medio del controlador tecnico .
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaTecnico extends JFrame  {
	
	public JList list = new JList();
	private JPanel contentPane;
	private ControladorTecnico ct;
	public static VentanaTecnico tecnico;
	public JTextField textField;
	public final DefaultListModel<String> modelo = new DefaultListModel<String>();
	public static int NombreDelTecnico;
	Conexion datos= new Conexion("EasyCardio.db");
	private JButton cerrarSesion;
	public Vector <String> PacienteTecnico=datos.PacienteTecnico();
	public static String nombre;
	public void VentanaTecnico(boolean control,String nombre_) {
		 this.nombre=nombre_;
			 crearVista(control);
 
	}
	public void addController (ControladorTecnico ct){
		 
		this.ct=ct;
	}


	public void crearVista(boolean control) {
		if(true) {
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
			int height = pantalla.height;
			int width = pantalla.width;
			setSize(width/2, height/2);
		      setLocationRelativeTo(null);		
		      setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(176, 224, 230));

		 for(int x=0;x<PacienteTecnico.size();x++){
		       modelo.addElement(PacienteTecnico.get(x));;
			 }
		}	
		 
		textField = new JTextField();
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		cerrarSesion = new JButton("Cerrar Sesion");
		

		
		JLabel lblPacientesRegistrados = new JLabel("Pacientes Registrados en el Sistema");
		lblPacientesRegistrados.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
		
		
		JLabel lblTecnico = new JLabel("Tecnico: " + nombre);
		lblTecnico.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		list.setModel(modelo);
		list.addMouseListener(ct);
		textField.addKeyListener(ct);
		scrollPane.setViewportView(list);
		list.setModel(modelo);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(456)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(cerrarSesion)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTecnico)
								.addComponent(lblPacientesRegistrados, GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE))))
					.addGap(11))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblPacientesRegistrados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTecnico)))
					.addGap(7)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cerrarSesion)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		cerrarSesion.setActionCommand(ControladorTecnico.cerrarSesion);
		cerrarSesion.addActionListener(ct);
		

	}
	}
