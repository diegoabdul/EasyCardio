package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import control.ControladorAdmin;
import control.ControladorCambioDatos;
import control.ControladorModificar;

import javax.swing.JButton;

public class VentanaCambioDatos extends JFrame{

	private JFrame frame;
	//private ControladorCambioDatos cb;
	private ControladorCambioDatos cDatos;
	private JButton btnModificarDatos;
	private JButton btnEliminarUsuario;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public VentanaCambioDatos() {
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2, height/2);
		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 200,200);
		
		 setLocationRelativeTo(null);
		 setResizable(false);
		 setVisible(true);
		
		btnModificarDatos = new JButton("Modificar datos");
		btnModificarDatos.setBounds(35, 34, 130, 29);
		getContentPane().add(btnModificarDatos);
		
		btnModificarDatos.addActionListener(cDatos);	
		btnModificarDatos.setActionCommand(ControladorCambioDatos.modifDatos);
		
		btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.setBounds(35, 99, 136, 29);
		getContentPane().add(btnEliminarUsuario);
		
		btnEliminarUsuario.addActionListener(cDatos);	
		btnEliminarUsuario.setActionCommand(ControladorCambioDatos.eliminar);
		
		
		
		
		
		
	}

	
	public void addController(ControladorCambioDatos _cDatos){
		cDatos=_cDatos;
		btnModificarDatos.addActionListener(cDatos);
		btnEliminarUsuario.addActionListener(cDatos);
		
	}

}