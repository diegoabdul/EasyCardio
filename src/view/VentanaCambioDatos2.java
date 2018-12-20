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

import control.ControladorAdmin;
import control.ControladorCambioDatos;
import control.ControladorCambioDatos2;
import control.ControladorModificar;
import model.Conexion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class VentanaCambioDatos2  extends JFrame{

	private ControladorCambioDatos2 cmb2;
	public static JTextField textField_Nombre;
	public static JTextField textField_DNI;
	public static JTextField textField_NombreID;
	private JPanel contentPane;
	public static JLabel ID;
	Conexion datos= new Conexion("EasyCardio.db");

	private JButton btnModificar;
	
	
	
	 public VentanaCambioDatos2 (){
	 	
	 	
	 	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2, height/2);
		setBounds(100, 100, 411, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		 setLocationRelativeTo(null);
		 setVisible(true);
			getContentPane().setBackground(new Color(176, 224, 230));

		 
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
			
			JLabel lblAgregarPaciente = new JLabel("Modificar Datos");
			lblAgregarPaciente.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		 
		 JLabel lblId = new JLabel("Nombre:");

		 JLabel lblNombre = new JLabel("DNI:");

		 JLabel lblDni = new JLabel("ID:");

		 JLabel lblSeguridadSocial = new JLabel("NombreID:");
		 
		int ID2= datos.MedicoID2(ControladorAdmin.a);
		if(ID2==0) {
			ID2= datos.TecnicoID2(ControladorAdmin.a);
		}
		
		ID = new JLabel(String.valueOf(ID2));

		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		
		textField_DNI = new JTextField();
		textField_DNI.setColumns(10);
		
		
		textField_NombreID = new JTextField();
		textField_NombreID.setColumns(10);
	 	
		btnModificar = new JButton("Modificar");
	 	
	 	
	 	GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId)
								.addComponent(lblNewLabel)
								.addComponent(lblNombre)
								.addComponent(lblDni)
								.addComponent(lblSeguridadSocial))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAgregarPaciente)
								.addComponent(textField_DNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_NombreID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnModificar))
					.addContainerGap(4, Short.MAX_VALUE))
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
						.addComponent(textField_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField_DNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_NombreID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeguridadSocial))
					.addGap(18)
					.addComponent(btnModificar)
					.addContainerGap(60, Short.MAX_VALUE))
		);
	 	
		contentPane.setLayout(gl_contentPane);
	 	btnModificar.addActionListener(cmb2);
	 	btnModificar.setActionCommand(ControladorCambioDatos2.modifDatos2);
	 	
		
		
	 }
	
	 public void addController(ControladorCambioDatos2 _cmb2){
		this.cmb2 = _cmb2;
		btnModificar.addActionListener(cmb2);
	 }
}