package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorAdmin;
import model.Conexion;
import model.Usuario;
/**
 * Representacion de la ventana admin por medio del controlador admin.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaAdmin extends JFrame  {

	private ControladorAdmin ca;
	public JList list = new JList();   
	private JPanel contentPane;
	JLabel titulo;
	private JButton botonAgregar;
	
	private JButton cerrarSesion;
	public JRadioButton radioButtonMedico;
	public JRadioButton radioButtonVerAmbos;
	public JRadioButton radioButtonTecnico;
	public static VentanaAdmin admin;
	public DefaultListModel modelo = new DefaultListModel();
	private JScrollPane scrollPane = new JScrollPane();
	Conexion datos= new Conexion("EasyCardio.db");

	
	
	public void VentanaAdmin(boolean control2) {
		String nombre;
		 crearVista(control2);
		
	}
	public void addController (ControladorAdmin _ca){		 
		ca=_ca;
		
	}
	public void crearVista(boolean control) {
		if(control) {
			
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
		JScrollPane scrollPane = new JScrollPane();
		
		
		radioButtonTecnico = new JRadioButton("Ver Tecnicos");
		
		radioButtonMedico = new JRadioButton("Ver Medicos");
		
		radioButtonVerAmbos = new JRadioButton("Ver Ambos");
		
		cerrarSesion = new JButton("Cerrar Sesion");

		botonAgregar = new JButton("A\u00F1adir Tecnico/Medico");
		
		
		JLabel lblNewLabel = new JLabel("Visualizar Tecnicos/Medicos");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
			
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(botonAgregar)
									.addPreferredGap(ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
									.addComponent(cerrarSesion))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(radioButtonMedico)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButtonTecnico)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButtonVerAmbos))))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 289, Short.MAX_VALUE)))
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
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButtonMedico)
						.addComponent(radioButtonTecnico)
						.addComponent(radioButtonVerAmbos))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cerrarSesion)
						.addComponent(botonAgregar))
					.addContainerGap())
		);
		
		scrollPane.setViewportView(list);
		list.setModel(modelo);
		list.addMouseListener(ca);
		list.setModel(modelo);
		
		ButtonGroup editableGroup = new ButtonGroup();
		editableGroup.add(botonAgregar);
		editableGroup.add(radioButtonMedico);
		editableGroup.add(radioButtonTecnico);
		editableGroup.add(radioButtonVerAmbos);
		editableGroup.add(cerrarSesion);
		
		
		botonAgregar.addActionListener(ca);	
		botonAgregar.setActionCommand(ControladorAdmin.modificar);
		
		radioButtonMedico.addActionListener(ca);
		radioButtonMedico.setActionCommand(ControladorAdmin.Medico);
		
		radioButtonTecnico.addActionListener(ca);
		radioButtonTecnico.setActionCommand(ControladorAdmin.Tecnico);
		
		radioButtonVerAmbos.addActionListener(ca);
		radioButtonVerAmbos.setActionCommand(ControladorAdmin.verAmbos);
		
		cerrarSesion.addActionListener(ca);
		cerrarSesion.setActionCommand(ControladorAdmin.cerrarSesion);
		
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		}
	}

}