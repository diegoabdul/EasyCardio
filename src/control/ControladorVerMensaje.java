package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.omg.CORBA.IRObject;

import model.Conexion;
import view.VentanaEscribirMensaje;
import view.VentanaLogin;
import view.VentanaVerMensaje;

public class ControladorVerMensaje implements ActionListener,MouseListener{
	
	
	public static VentanaVerMensaje ventanaVerMensaje;
	public ControladorTecnicoGrafica ctg;
	Conexion datos= new Conexion("EasyCardio.db");


	
	public ControladorVerMensaje(VentanaVerMensaje vvm, ControladorTecnicoGrafica _ctg) {
		super();
		ControladorVerMensaje.ventanaVerMensaje = vvm;
		ctg = _ctg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			 JOptionPane.showMessageDialog(null,ventanaVerMensaje.table.getValueAt(ventanaVerMensaje.table.getSelectedRow(), 3).toString() );
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
