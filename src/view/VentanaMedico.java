package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControladorMedico;
import control.ControladorTecnico;
import model.Conexion;
import model.Usuario;
/**
 * Representacion de la ventana medico por medio del controlador medico.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class VentanaMedico extends JFrame {
	
	public String MedicoActual;
	public JList list = new JList();
	public JPanel contentPane;
	private ControladorMedico cm;
	public static VentanaMedico vMedico;
	public JTextField textField;
	public final DefaultListModel<String> modelo = new DefaultListModel<String>();
	public static int y;
	private JButton cerrarSesion;
	public String relacion2;
	private JButton btnModificar;


	
	public void VentanaTecnico(String NombreID,Conexion datos,boolean control) {

		MedicoActual=NombreID;
				 crearVista(NombreID,datos,control);
 
	}
	public void addController (ControladorMedico cm){
		 
		this.cm=cm;
	}
	
	
	
	public void crearVista(String NombreID,Conexion datos,boolean control) {
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
		
		Vector <String> PacienteMedico=datos.PacienteMedico(NombreID);
	
		for(int x=0;x<PacienteMedico.size();x++){
		modelo.addElement(PacienteMedico.get(x));
		}
		      if( PacienteMedico.size()==0) {
		    	  Vector <String> PacienteRandom=datos.PacienteTecnico();
		    	  for(int x=0;x<PacienteRandom.size();x++){
		  			modelo.addElement(PacienteRandom.get(x));
		  		}
		      }
			 
		}
JScrollPane scrollPane = new JScrollPane();
		
		cerrarSesion = new JButton("Cerrar Sesion");
		
		JLabel lblPacientesRegistrados = new JLabel("Pacientes Registrados en el Sistema");
		lblPacientesRegistrados.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		
		btnModificar = new JButton("Agregar Paciente");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblMedico = new JLabel("Medico: " + MedicoActual);
		lblMedico.setFont(new Font("Lucida Grande", Font.BOLD, 19));

		list.setModel(modelo);
		list.addMouseListener(cm);
		textField.addKeyListener(cm);
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
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnModificar)
									.addPreferredGap(ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
									.addComponent(cerrarSesion))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMedico)
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
							.addComponent(lblMedico)))
					.addGap(7)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cerrarSesion)
						.addComponent(btnModificar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		cerrarSesion.addActionListener(cm);
		cerrarSesion.setActionCommand(ControladorMedico.cerrar);
		btnModificar.addActionListener(cm);
		btnModificar.setActionCommand(ControladorMedico.nuevo);
	}
	
}

