package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorCambioDatos;
import control.ControladorNuevoPaciente;
import model.Conexion;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaNuevoPaciente extends JFrame{

	private JFrame frame;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_5;
	ControladorNuevoPaciente cnp;
	private JPanel contentPane;
	private JButton btnAadirPaciente;
	public static JComboBox comboBox;
	public static JComboBox comboBoxMuni;
	Conexion datos= new Conexion("EasyCardio.db");
	Vector<String> asignar = datos.AsignarMedico();
	Vector<String> municipios = datos.Municipios();
	public static JComboBox comboBox2;
	String[] sexo = {"mujer","hombre"};
	public static JComboBox comboBox3;
	String[] estado = {"Leve","Bajo","Moderado","Grave","Critico"};
	String identificador = datos.CountPacientes();
	public static JLabel lblPacientes;
	public VentanaNuevoPaciente() {
		int pacientes = Integer.parseInt(identificador);
		pacientes=pacientes+1;
		setBounds(100, 100, 800, 553);
	    setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(176, 224, 230));

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
		
		JLabel lblAgregarPaciente = new JLabel("Agregar Paciente");
		lblAgregarPaciente.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		
		JLabel lblId = new JLabel("ID:");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		JLabel lblIdMedico = new JLabel("ID Medico:");
		
		JLabel lblDni = new JLabel("DNI:");
		
		JLabel lblSeguridadSocial = new JLabel("Seguridad Social:");
		
		JLabel lblDireccion = new JLabel("Direccion:");
		
		JLabel lblEstado = new JLabel("Estado:");

		lblPacientes = new JLabel(String.valueOf(pacientes));
			
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
			
		comboBox3 = new JComboBox(estado);
		
		comboBox2 = new JComboBox(sexo);
		
		comboBox = new JComboBox(asignar);

		comboBoxMuni = new JComboBox(municipios);
		
		btnAadirPaciente = new JButton("Agregar Paciente");

		
		btnAadirPaciente.addActionListener(cnp);
	
		try{
    		datos.c.close();
    	}catch (Exception e){
    		e.toString();
    	}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId)
								.addComponent(lblNewLabel)
								.addComponent(lblNombre)
								.addComponent(lblDni)
								.addComponent(lblSeguridadSocial)
								.addComponent(lblIdMedico)
								.addComponent(lblSexo)
								.addComponent(lblEstado)
								.addComponent(lblDireccion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAgregarPaciente)
								.addComponent(lblPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBox2, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBox3, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxMuni, Alignment.LEADING))))
						.addComponent(btnAadirPaciente))
					.addContainerGap(19, Short.MAX_VALUE))
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
						.addComponent(lblPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeguridadSocial))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBoxMuni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSexo)
						.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdMedico)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(btnAadirPaciente))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	
	}

	public void addController(ControladorNuevoPaciente _cp){
		cnp=_cp;
		btnAadirPaciente.addActionListener(cnp);
		
		
	}
}
