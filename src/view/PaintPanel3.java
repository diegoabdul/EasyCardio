package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Conexion;
/**
 * Metodo para pintar la imagen del logo con el logo del hombre para ser solo llamado por otras clases y representarlo.
 * 
 * @author Zoran Cerrillo Del Pino, Diego Abdul Massih, Reda Chakor Bouhake.
 * @version 1.0.
 * @since 18-3-2018.
 *
 */
public class PaintPanel3 extends javax.swing.JPanel {
	Conexion datos= new Conexion("EasyCardio.db");
	public void Imagen() {
		this.setSize(400, 400); 
	}
	public void paint(Graphics grafico) {
	
		Dimension height = getSize();

		Image Img = new ImageIcon(datos.getPhotoHombre()).getImage();
		grafico.drawImage(Img, 0, 0, height.width, height.height, null);

		setOpaque(false);
		super.paintComponent(grafico);
	}
}