package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ControladorEscribirMensaje;
import control.ControladorVerMensaje;
import model.Conexion;

public class VentanaVerMensaje extends JFrame {
	
	private JPanel contentPane;
	public ControladorVerMensaje cvm;
	Conexion datos= new Conexion("EasyCardio.db");
	public JTable table;
	
	
	public void crearVista() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setSize(width/2, height/2);		
		setBounds(100, 100, 683, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(176, 224, 230));

		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(datos.getPhotoLogoVentanas()));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblVerMensajes = new JLabel("Ver Mensajes");
		lblVerMensajes.setFont(new Font("Lucida Grande", Font.BOLD, 26));

		
		JSeparator separator = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addGap(44)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblVerMensajes, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
										.addGap(112))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(separator, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
										.addGap(10)))
								.addGap(0))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
								.addContainerGap())))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(4))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblVerMensajes, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 3, Short.MAX_VALUE)
								.addGap(66)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
						.addContainerGap())
			);
			table = new JTable();
			table.setDefaultEditor(Object.class, null);
			DefaultTableModel modelo;
			modelo = new DefaultTableModel();
			table.setModel(modelo);
			modelo.addColumn("ID_Mensajes");
			modelo.addColumn("Fecha");
			modelo.addColumn("Paciente");
			modelo.addColumn("Mensaje");
			modelo.addColumn("Tecnico");
			modelo.addColumn("Medico");
			ResultSet rs = datos.VerMensajes(datos.ConsultaPacienteNombre(VentanaTecnicoGrafica.i).toString());
			Object [] fila=new Object[6]; 
			try {
				while ( rs.next() ) {
			        fila[0]=rs.getInt("ID_Mensajes");
			        fila[1]=rs.getString("Fecha");
			        fila[2]=rs.getString("Paciente");
			        fila[3]=rs.getString("Mensaje");
			        fila[4]=rs.getString("Tecnico");
			        fila[5]=rs.getString("Medico");
			        
			        modelo.addRow(fila); 
			        table.setModel(modelo);
				}
				rs.close();
				datos.stmt.close();
				datos.c.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		table.addMouseListener(cvm);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	public void addController (ControladorVerMensaje cvm){
		 
		this.cvm=cvm;
	
	}

}


