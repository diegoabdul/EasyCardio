

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControladorModificar;
/**
 * Representacion de la ventana modificar por medio del controlador modificar.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaModificar extends JFrame{
	
	private ControladorModificar cModificar;
	private JPanel contentPane;
	public static JTextField nombre;
	public static JTextField correo;
	public static JComboBox comboBox;
	private JButton botonAnadir;
	String[] roles = {"Medico","Tecnico"};


	

	
	public VentanaModificar(){
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2, height/2);
		setBounds(100, 100, 406, 366);

	    setLocationRelativeTo(null);		
	    setVisible(true);
		contentPane = new JPanel();
		

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);
		JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos :");
		
		nombre = new JTextField();
		nombre.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol :");
		comboBox = new JComboBox(roles);

		
		JLabel lblNewLabel = new JLabel("DNI :");
		
		botonAnadir = new JButton("Agregar");

		
		correo = new JTextField();
		correo.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(258)
								.addComponent(botonAnadir, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(25)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNombreYApellidos, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblRol, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
												.addGap(26)))
										.addGap(18))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
										.addGap(26)))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(correo, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(comboBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(nombre, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
						.addGap(24))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(41)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNombreYApellidos, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
						.addGap(46)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(7)
								.addComponent(lblRol, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)))
						.addGap(48)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
							.addComponent(correo, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
						.addGap(47)
						.addComponent(botonAnadir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(28))
			);
		contentPane.setLayout(gl_contentPane);
		
	}

		public void addController(ControladorModificar _cModificar){
		cModificar=_cModificar;
		botonAnadir.addActionListener(cModificar);
	}
}