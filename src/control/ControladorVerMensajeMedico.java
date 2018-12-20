package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import model.Conexion;
import view.VentanaVerMensaje;
import view.VentanaVerMensajeMedico;

public class ControladorVerMensajeMedico implements ActionListener,MouseListener {

	
	public static VentanaVerMensajeMedico ventanaVerMensajeMedico;
	public ControladorMedicoGrafica cmg;
	Conexion datos= new Conexion("EasyCardio.db");


	
	public ControladorVerMensajeMedico(VentanaVerMensajeMedico vvmm, ControladorMedicoGrafica _cmg) {
		super();
		ControladorVerMensajeMedico.ventanaVerMensajeMedico = vvmm;
		cmg = _cmg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			 JOptionPane.showMessageDialog(null,ventanaVerMensajeMedico.table.getValueAt(ventanaVerMensajeMedico.table.getSelectedRow(), 3).toString() );
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
