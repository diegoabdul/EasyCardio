package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import control.MainController;
/**
 * Representacion de la ventana login por medio del main controller.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaLogin extends JFrame {

	MainController mc;
	public static JPasswordField passwordField_1;
	public static JTextField textField_1;
	

	public void addController (MainController mc){
		 
		this.mc=mc;
	}
	public void crearVista() {
		
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize(). width);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize(). height);
		setBounds(0, 0, ancho, alto);
		//setTitle("VENTANA DE INICIO DE SESION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		getContentPane().setBackground(new Color(176, 224, 230));
		
		JButton botonLogin = new JButton("Iniciar Sesion");
		springLayout.putConstraint(SpringLayout.WEST, botonLogin, (int) (ancho/1.87), SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, botonLogin, (int) (alto/1.5), SpringLayout.NORTH, getContentPane());
		botonLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
	
		getContentPane().add(botonLogin);
		botonLogin.addActionListener(mc);
		botonLogin.setActionCommand(MainController.BOTON);
	
		JPanel imagen = new PaintPanel();
		
		springLayout.putConstraint(SpringLayout.NORTH, imagen, alto/12, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, imagen, (int) (ancho/2.5), SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, imagen, (int) (alto/2.2), SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, imagen, (int) (ancho/-3.1), SpringLayout.EAST, getContentPane());
		getContentPane().add(imagen);
		
		
		passwordField_1 = new JPasswordField();
		
		springLayout.putConstraint(SpringLayout.WEST, passwordField_1, (int) (ancho/2.3), SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, passwordField_1, (int) (alto/1.7), SpringLayout.NORTH, getContentPane());
		getContentPane().add(passwordField_1);
		passwordField_1.setColumns(25);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_1, (int) (ancho/2.3), SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, alto/2, SpringLayout.NORTH, getContentPane());
		getContentPane().add(textField_1);
		textField_1.setColumns(25);
		
		JLabel user = new JLabel("Usuario");
		springLayout.putConstraint(SpringLayout.NORTH, user, alto/2, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, user, ancho/3, SpringLayout.WEST, getContentPane());
		user.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(user);
		
		JLabel password = new JLabel("Contrasena");
		password.setFont(new Font("Times New Roman", Font.BOLD, 18));
		springLayout.putConstraint(SpringLayout.WEST, password, ancho/3, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, password, (int) (alto/1.7), SpringLayout.NORTH, getContentPane());
		getContentPane().add(password);
		
		setResizable(false);
		setVisible(true);	

		}
	}

