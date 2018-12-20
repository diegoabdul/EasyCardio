package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import control.ControladorModificar;
import model.Conexion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class VentanaGestion  extends JFrame{

	private ControladorModificar cModificar;
	
	public static JTextField ID;
	public static JTextField NombreId;
	public static JTextField Nombre;
	public static JTextField DNI;
	Conexion datos= new Conexion("EasyCardio.db");

	private JLabel lblRol;
	private JPanel contentPane;
	private JButton btnAnadir;
	public static JComboBox comboBox;
	String[] roles = {"Medico","Tecnico"};
	
	public VentanaGestion(){
	
		
		 	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
			int height = pantalla.height;
			int width = pantalla.width;
			setSize(width/2, height/2);
			setBounds(100, 100, 412, 441);
		    setLocationRelativeTo(null);
		    setVisible(true);
			contentPane = new JPanel();
			

			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			getContentPane().setBackground(new Color(176, 224, 230));
		
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
			
			JLabel lblAgregarPaciente = new JLabel("Agregar Usuario");
			lblAgregarPaciente.setFont(new Font("Lucida Grande", Font.BOLD, 26));
			
			JLabel lblId = new JLabel("Rol:");
			
			JLabel lblNombre = new JLabel("Nombre:");
			
			JLabel lblDni = new JLabel("ID:");
			
			JLabel lblSeguridadSocial = new JLabel("NombreID:");
			
			JLabel lblDireccion = new JLabel("DNI:");
			
			ID = new JTextField();
			ID.setColumns(10);
			
			NombreId = new JTextField();
			NombreId.setColumns(10);
			
			Nombre = new JTextField();
			Nombre.setColumns(10);
			
			DNI = new JTextField();
			DNI.setColumns(10);
			
			btnAnadir = new JButton("Agregar");
		
		comboBox = new JComboBox(roles);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId)
						.addComponent(lblNewLabel)
						.addComponent(lblNombre)
						.addComponent(lblDni)
						.addComponent(lblSeguridadSocial)
						.addComponent(lblDireccion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAgregarPaciente)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(NombreId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(DNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAnadir)
					.addContainerGap(247, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(lblAgregarPaciente)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(NombreId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeguridadSocial))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(DNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnAnadir)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	 }
	
	public void addController(ControladorModificar _cModificar){
		cModificar=_cModificar;
		btnAnadir.addActionListener(cModificar);
	}
}