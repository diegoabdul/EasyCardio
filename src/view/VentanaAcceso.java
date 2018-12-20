package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorTecnico;
/**
 * Representacion de la ventana acceso por medio del controlador tecnico.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaAcceso extends JFrame{
	
	private JPanel contentPane;
	public JTextField textField;
	private ControladorTecnico ct;
	private JButton botonAceptar;
	
	public void addController (ControladorTecnico ct){
		 
		this.ct=ct;
	}

	
	public void crearVista() {
		//this.setBounds(100, 100, 323, 152);
		setBounds(100, 100, 402, 171);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
	      setLocationRelativeTo(null);		
	      setVisible(true);
		JLabel lblIntroduzcaDni = new JLabel("Introduzca el DNI : ");
		
		botonAceptar = new JButton("Aceptar");
		
		this.textField = new JTextField();
		this.textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(botonAceptar, Alignment.TRAILING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lblIntroduzcaDni, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGap(37)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblIntroduzcaDni, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(botonAceptar)
						.addContainerGap())
			);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		botonAceptar.addActionListener(ct);
		botonAceptar.setActionCommand(ControladorTecnico.aceptar);
	}
}



