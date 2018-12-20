package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorMedicoGrafica;
import control.ControladorTecnicoGrafica;
import model.Conexion;
import model.Usuario;
/**
 * Representacion de la ventana medico grafica por medio del controlador medico grafica.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
	public class VentanaMedicoGrafica extends JFrame {

		private JPanel contentPane;
		private ControladorMedicoGrafica cmg;
		public JList lista = new JList();
		public final DefaultListModel modelo = new DefaultListModel();
		public int y;
		public JMenuItem mntmNewMenuItem_escribirComentario;
		public JMenuItem mntmNewMenuItem_verComentario;
		public JScrollPane scrollPane = new JScrollPane();
		public JPanel imagen;
		public static int id;
		public JCheckBox subirGrafica;
		public JButton info ;
		
		
		@SuppressWarnings("deprecation")
		public VentanaMedicoGrafica(String identificador, Conexion datos) {
			setDefaultCloseOperation(VentanaMedicoGrafica.DISPOSE_ON_CLOSE);
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
			int height = pantalla.height;
			int width = pantalla.width;
			setSize((int) (width/1.8), (int) (height/1.8));
		    setLocationRelativeTo(null);

		    setVisible(true);
		    contentPane = new JPanel();
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			getContentPane().setBackground(Color.WHITE);
			subirGrafica = new JCheckBox("Comparar ECG");
			getContentPane().setBackground(new Color(176, 224, 230));

			
			info = new JButton ("Ayuda");
			info.setActionCommand(ControladorMedicoGrafica.ayuda);
			//ImageIcon icono = new ImageIcon (logoInfo.jpg);
			//info.setIcon(icono);
	
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnNewMenu = new JMenu("Comentarios");
			mnNewMenu.setMnemonic(KeyEvent.VK_U);
			menuBar.add(mnNewMenu);
			
			mntmNewMenuItem_escribirComentario = new JMenuItem("Escribir comentario");
			
			mnNewMenu.add(mntmNewMenuItem_escribirComentario);
			
			mntmNewMenuItem_verComentario = new JMenuItem("Ver comentarios");
			
			mnNewMenu.add(mntmNewMenuItem_verComentario);
			
			JLabel lblNombre = new JLabel("Nombre :");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			JLabel lblDni = new JLabel("DNI :");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			JLabel lblDireccion = new JLabel("Direccion :");
			lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			JLabel lblNSeguridadSocial = new JLabel("N\u00BA Seguridad Social : ");
			lblNSeguridadSocial.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			JLabel lblEstadoDelPaciente = new JLabel("Estado del Paciente :");
			lblEstadoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
			JLabel lblComentarios = new JLabel("Historial :");
			lblComentarios.setFont(new Font("Tahoma", Font.PLAIN, 12));

			id=Integer.parseInt(identificador);
			
			JLabel lblNewLabel = new JLabel(datos.ConsultaPacienteNombre(id));

			JLabel lblAquiDni = new JLabel(datos.ConsultaPacienteDNI(id));
			
			JLabel lblAquiDireccion = new JLabel(datos.ConsultaPacienteDireccion(id));
			
			JLabel lblAquiSs = new JLabel(datos.ConsultaPacienteSS(id));
			
			JLabel lblAquiEstado = new JLabel(datos.ConsultaPacienteEstado(id));
			
			if((datos.ConsultaPacienteSexo(id)).matches("hombre")) {
				  imagen = new PaintPanel3();
			}
			if((datos.ConsultaPacienteSexo(id)).matches("mujer")) {
				 imagen = new PaintPanel4();
			}
			
			
			
			
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(imagen, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addGap(60)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNSeguridadSocial, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
										.addComponent(lblDni, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
										.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEstadoDelPaciente, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
											.addGap(76))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDireccion, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
											.addGap(68)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblAquiDni, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
											.addGap(77))
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblAquiDireccion, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
											.addGap(76))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAquiEstado, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
												.addComponent(lblAquiSs, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
											.addGap(74))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addGap(202)
									.addComponent(subirGrafica, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(info, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addGap(22))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblComentarios, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
									.addGap(496)))
							.addContainerGap())
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(imagen, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
									.addGap(36)
									.addComponent(lblComentarios, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 12, Short.MAX_VALUE)
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE))
									.addGap(26)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAquiDni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE))
									.addGap(26)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAquiDireccion, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDireccion))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(29)
											.addComponent(lblAquiSs, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(18)
											.addComponent(lblNSeguridadSocial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEstadoDelPaciente)
										.addComponent(lblAquiEstado, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(61)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(subirGrafica, GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
										.addComponent(info, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(26)))
							.addGap(4))
				);
				contentPane.setLayout(gl_contentPane);
				setResizable(false);
				
				
			}
		
		public void addController (ControladorMedicoGrafica _cmg){
			cmg=_cmg;
			cmg.ECG(id);
			lista.addMouseListener(cmg);
			subirGrafica.addActionListener(cmg);
			info.addActionListener(cmg);
			
			mntmNewMenuItem_escribirComentario.addActionListener(cmg);
			mntmNewMenuItem_escribirComentario.setActionCommand(ControladorTecnicoGrafica.escribirComentarios);
			
			mntmNewMenuItem_verComentario.addActionListener(cmg);
			mntmNewMenuItem_verComentario.setActionCommand(ControladorTecnicoGrafica.verComentarios);

		}
		
		}

