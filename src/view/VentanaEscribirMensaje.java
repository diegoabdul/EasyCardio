package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import control.ControladorEscribirMensaje;
import control.ControladorTecnico;
import model.Conexion;

public class VentanaEscribirMensaje extends JFrame{
	
	private JPanel contentPane;
	public ControladorEscribirMensaje cem;
	Conexion datos= new Conexion("EasyCardio.db");
	 private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 public Timestamp timestamp;
	 public JEditorPane editorPane;
	public void crearVista() {
			
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2, height/2);
		setBounds(100, 100, 690, 366);
	    setLocationRelativeTo(null);	
	    
	    setVisible(true);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(176, 224, 230));

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
		
		JLabel lblEscribirComentario = new JLabel("Escribir Comentario");
		lblEscribirComentario.setFont(new Font("Lucida Grande", Font.BOLD, 26));

		
		JSeparator separator = new JSeparator();
		
		JLabel lblDe = new JLabel("DE : ");

		JLabel lblNewLabel_1 = new JLabel(VentanaTecnico.nombre);
		
		JLabel lblPaciente = new JLabel("PACIENTE : ");
		
		JLabel lblNewLabel_2 = new JLabel(datos.ConsultaPacienteNombre(VentanaTecnicoGrafica.i));
		
		JLabel lblFecha = new JLabel("Fecha : ");
		 timestamp = new Timestamp(System.currentTimeMillis());
		JLabel lblNewLabel_3 = new JLabel(sdf.format(timestamp).toString());
		
		
		editorPane = new JEditorPane();
		
		JButton btnAgregarComentario = new JButton("Agregar Comentario");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblEscribirComentario, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(37)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(editorPane, Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblDe, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblPaciente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(91)))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(39)
														.addComponent(lblFecha)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
												.addGap(75))
											.addComponent(btnAgregarComentario))))
								.addGap(239))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(81)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(11)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblEscribirComentario, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblDe, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
									.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPaciente, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnAgregarComentario, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(8))
			);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		btnAgregarComentario.addActionListener(cem);
	}		
	
	public void addController (ControladorEscribirMensaje cem){
		 
		this.cem=cem;
	}
}
