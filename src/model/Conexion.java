package model;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import view.VentanaGestion;
import view.VentanaLogin;
import view.VentanaModificar;
import view.VentanaNuevoPaciente;
import view.VentanaCambioDatos2;

import java.sql.ResultSet;


public class Conexion {

	String BBDDName;
    public Connection c = null;
	public Statement stmt = null;
	public boolean medico=false;
	public boolean tecnico=false;
	public int PacienteActual;
	public Conexion(String path) {
		BBDDName = path;
	}
	
public ResultSet VerMensajes(String identificador) {
		ResultSet rs = null;	
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			rs = stmt.executeQuery( "select Mensajes.ID_Mensajes,Mensajes.Fecha,Mensajes.Paciente,Mensajes.Mensaje,Mensajes.Tecnico,Mensajes.Medico from Mensajes where Mensajes.Paciente="+"'"+identificador+"'order by Mensajes.ID_Mensajes asc");

			
//			rs.close();
//			stmt.close();
//			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return rs;
	}
	
	public boolean EscribirMensajeTecnico(String Fecha,String nombre,String Mensaje,String Tecnico) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			stmt.executeUpdate( "insert into Mensajes (Fecha,Paciente,Mensaje,Tecnico) values ('"+Fecha+"','"+nombre+"','"+Mensaje+"','"+Tecnico+"')");

			
			c.commit();
			stmt.close();
			c.close();
			return true;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return false;
	}
	
	public boolean EscribirMensajeMedico(String Fecha,String nombre,String Mensaje,String Tecnico) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			stmt.executeUpdate( "insert into Mensajes (Fecha,Paciente,Mensaje,Medico) values ('"+Fecha+"','"+nombre+"','"+Mensaje+"','"+Tecnico+"')");

			
			c.commit();
			stmt.close();
			c.close();
			return true;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return false;
	}
	
	public int MedicoID2(String identificador) {
		int MedicoID=0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Medico.ID from Medico where Medico.Nombre="+"'"+identificador+"'");
			while ( rs.next() ) {
				MedicoID = rs.getInt("ID");
				
		}
			
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return MedicoID;
	}
	
	public int TecnicoID2(String identificador) {
		int MedicoID=0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Tecnico.ID from Tecnico where Tecnico.Nombre="+"'"+identificador+"'");
			while ( rs.next() ) {
				MedicoID = rs.getInt("ID");
				
		}
			
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return MedicoID;
	}
	
public Vector<String> Municipios() {
		
		String name=null;
		String codigo=null;
		Vector <String> ConsultaMedicosAdmin = new Vector<String>();
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Municipios.Nombre,Municipios.CodigoPostal from Municipios order by Municipios.Nombre asc ");

			while ( rs.next() ) {
			
				name = rs.getString("Nombre");
				codigo = rs.getString("CodigoPostal");
				String devuelve = name+" "+codigo;
				ConsultaMedicosAdmin.add(devuelve);
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ConsultaMedicosAdmin;
	}
	
	public String CountPacientes() {

		String identificador=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select count(Paciente.ID) from Paciente" );

			while ( rs.next() ) {
					identificador =rs.getString("count(Paciente.ID)");

						 
		}

			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return identificador;
	}
public boolean InsertarInforme (String ECG,String id) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			stmt.executeUpdate( " update ECG set Informe='"+ECG+"' where ECG.NombreID='"+id+"'");
			
			c.commit();
			
			stmt.close();
			c.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return false;	
	}
	public Vector<String> AsignarECG() {

		String identificador=null;
		Vector <String> PacienteMedico = new Vector<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select ecg.NombreID from ECG" );

			while ( rs.next() ) {
					identificador =rs.getString("NombreID");
				    PacienteMedico.add(identificador);
						 
		}

			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return PacienteMedico;
	}
	
	public String ECGExistente(int id2) {
		String ID=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select ECG from PacienteECG where PacienteECG.IDPaciente="+"'"+id2+"'" );

			while ( rs.next() ) {
				ID = rs.getString("ECG");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ID;
	}
	
public boolean InsertarECG (String ECG,int id) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			stmt.executeUpdate( "update PacienteECG set ECG="+"'"+ECG+"' where PacienteECG.IDPaciente="+id+"");
			

			c.commit();
			
			stmt.close();
			c.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return false;	
	}
	
	public String ConsultaMedicoID(String identificador) {
		String ID=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Medico.ID from Medico where Medico.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				ID = rs.getString("ID");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ID;
	}
	
	public String ConsultaTecnicoID(String identificador) {
		String ID=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Tecnico.ID from Tecnico where Tecnico.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				ID = rs.getString("ID");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ID;
	}
	
	public String ConsultaPacienteID(String identificador) {
		String ID=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.ID from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				ID = rs.getString("ID");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ID;
	}
	
	public Vector<String> AsignarMedico() {

		String identificador=null;
		String nombre=null;
		Vector <String> PacienteMedico = new Vector<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Medico.ID,Medico.Nombre from Medico" );

			while ( rs.next() ) {
					identificador =rs.getString("ID");
					nombre =rs.getString("Nombre");
					String devolver = identificador+" "+nombre;
				    PacienteMedico.add(devolver);
					
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return PacienteMedico;
	}
	
public void ValidarMedico() {
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		String DNI=null;
		int ID=0;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "select Medico.DNI,Medico.ID from Medico where Medico.DNI="+"'"+VentanaGestion.DNI.getText().toString()+"'"+"and Medico.ID="+"'"+VentanaGestion.ID.getText().toString()+"'");
		
		while ( rs.next() ) {
			DNI = rs.getString("DNI");
			ID = rs.getInt("ID");
		}

		if(DNI!=null&&ID!=0) {
			medico=true;
			
		}
		rs.close();
		stmt.close();
//		c.close();
		medico=false;

	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
}
public void ValidarTecnico() {
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		String DNI=null;
		int ID=0;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "select Tecnico.DNI,Tecnico.ID from Tecnico where Tecnico.DNI="+"'"+VentanaGestion.DNI.getText().toString()+"'"+"and Tecnico.ID="+"'"+VentanaGestion.ID.getText().toString()+"'");
		
		while ( rs.next() ) {
			DNI = rs.getString("DNI");
			ID = rs.getInt("ID");
		}

		if(DNI!=null&&ID!=0) {
			
			tecnico=true;
		}
		
		rs.close();
		stmt.close();
//		c.close();
		tecnico=false;
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
}
	public boolean InsertarMedico () {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ValidarMedico();
			if(medico) {
				 JOptionPane.showMessageDialog(null, "Medico ya existente  ");

			}if(medico==false) {
			stmt.executeUpdate( " insert into Medico (ID,NombreID,Nombre,DNI) values ('"+ VentanaGestion.ID.getText().toString() +"', '" + VentanaGestion.NombreId.getText().toLowerCase().toString()+ "' , '" + VentanaGestion.Nombre.getText().toString() + "', '" + VentanaGestion.DNI.getText().toString() + "')");
			c.commit();
			stmt.executeUpdate( " insert into Usuarios (Contrasena,ID) values ('"+VentanaGestion.DNI.getText().toLowerCase().toString()+"' , '" + VentanaGestion.ID.getText().toString() +"')");
			}
			c.commit();
			
			stmt.close();
			c.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return false;	
	}
	
public boolean InsertarTecnico () {
		

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			ValidarTecnico();
			stmt = c.createStatement();
			if(tecnico) {
				 JOptionPane.showMessageDialog(null, "Tecnico ya existente  ");

			}
			if(tecnico==false) {
			stmt.executeUpdate( " insert into Tecnico (ID,NombreID,Nombre,DNI) values ('"+ VentanaGestion.ID.getText().toString() +"', '" + VentanaGestion.NombreId.getText().toLowerCase().toString()+ "' , '" + VentanaGestion.Nombre.getText().toString() + "', '" + VentanaGestion.DNI.getText().toString() + "')");
			c.commit();
			stmt.executeUpdate( " insert into Usuarios (Contrasena,ID) values ('"+VentanaGestion.DNI.getText().toLowerCase().toString()+"' , '" + VentanaGestion.ID.getText().toString() +"')");
			}
			c.commit();
			
			stmt.close();
			c.commit();
			c.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
			return false;
	}

public void InsertarPaciente() { 
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		stmt = c.createStatement();
		String[] parts = VentanaNuevoPaciente.comboBox.getSelectedItem().toString().split(" ");
		String identificador = parts[0];
		stmt.executeUpdate( "insert into Paciente (ID,Nombre,DNI,SeguridadSocial , Direccion, Estado, Sexo , MedicoID) values ('" + VentanaNuevoPaciente.lblPacientes.getText().toString()+ "','" + VentanaNuevoPaciente.textField_1.getText().toString() + "','" + VentanaNuevoPaciente.textField_2.getText().toString() + "','" + VentanaNuevoPaciente.textField_3.getText().toString() + "','" + VentanaNuevoPaciente.comboBoxMuni.getSelectedItem().toString() + "','" + VentanaNuevoPaciente.comboBox3.getSelectedItem().toString() + "','" + VentanaNuevoPaciente.comboBox2.getSelectedItem().toString() + "','" + identificador + "')");
		
		
	
		stmt.close();
		c.commit();
		c.close();
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
		
}
public void ModificarMedico(String chosen) { // Diferenciar medico o tecnico elegido
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);

		stmt = c.createStatement();

		stmt.executeUpdate( "update Medico set ID =" + VentanaCambioDatos2.ID.getText() + ", NombreID ='" + VentanaCambioDatos2.textField_NombreID.getText() + "',Nombre ='" + VentanaCambioDatos2.textField_Nombre.getText() + "',DNI ='" + VentanaCambioDatos2.textField_DNI.getText() + "' where Nombre = '" + chosen + "'");
		
			
	
		stmt.close();
		c.commit();
		c.close();
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
		
}

public void ModificarTecnico(String chosen) { // Diferenciar medico o tecnico elegido
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);

		stmt = c.createStatement();

		stmt.executeUpdate( "update Tecnico set ID =" + VentanaCambioDatos2.ID.getText() + ", NombreID ='" + VentanaCambioDatos2.ID.getText() + "',Nombre ='" + VentanaCambioDatos2.textField_Nombre.getText() + "',DNI ='" + VentanaCambioDatos2.textField_DNI.getText() + "' where Nombre = '" + chosen + "'");
		
			
	
		stmt.close();
		c.commit();
		c.close();
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
		
}
	
	public void eliminarTecnico( String chosen) {
		
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				int ID=0;
				
				ResultSet rs = stmt.executeQuery( "select Tecnico.ID from Tecnico where Nombre="+"'"+chosen+"'" );
						
					while ( rs.next() ) {
						ID = rs.getInt("ID");
					}	
			
				stmt.executeUpdate("delete from Tecnico where Nombre ='"+ chosen +"'");
				c.commit();
				stmt.executeUpdate("delete from Usuarios where ID ="+ID+"");
				rs.close();
				stmt.close();
				c.commit();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}

	}
	
	public void eliminarMedico( String chosen) {
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			int ID=0;
			ResultSet rs = stmt.executeQuery( "select Medico.ID from Medico where Nombre="+"'"+chosen+"'" );
			while ( rs.next() ) {
				ID = rs.getInt("ID");
			}		
			
			stmt.executeUpdate("delete from Medico where Nombre = '"+ chosen +"' ");
			c.commit();
			stmt.executeUpdate("delete from Usuarios where ID ="+ID+"");
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

}
	public boolean LoginMedico(String NombreID,String contrasena) {
		String id=null;
		String name=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
//			Class.forName("org.mariadb.jdbc.Driver");
//			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Usuarios.Contrasena,Medico.NombreID from Usuarios join Medico on Medico.ID=Usuarios.ID where Medico.NombreID like "+"'"+NombreID+"'" );

			while ( rs.next() ) {
				id = rs.getString("Contrasena");
				name = rs.getString("NombreID");
			}
			
			rs.close();
			stmt.close();
			c.close();
			if((NombreID.equals(name))&&(contrasena.equals(id))) {
				return true;
			}
			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}
	
	public boolean LoginTecnico(String NombreID,String contrasena) {
		String id=null;
		String name=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Usuarios.Contrasena,Tecnico.NombreID from Usuarios join Tecnico on Tecnico.ID=Usuarios.ID where Tecnico.NombreID like "+"'"+NombreID+"'" );

			while ( rs.next() ) {
				id = rs.getString("Contrasena");
				name = rs.getString("NombreID");
			}
			
			rs.close();
			stmt.close();
			c.close();
			if((NombreID.equals(name))&&(contrasena.equals(id))) {
				return true;
			}
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}
	
	public boolean LoginAdmin(String NombreID,String contrasena) {

		
		String id=null;
		String name=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery( "select Usuarios.Contrasena,Admin.NombreID from Usuarios join Admin on Admin.ID=Usuarios.ID where Admin.NombreID like "+"'"+NombreID+"'" );

			while ( rs.next() ) {
				id = rs.getString("Contrasena");
				name = rs.getString("NombreID");
			}
				
			rs.close();
			stmt.close();
			c.close();
			if((NombreID.equals(name))&&(contrasena.equals(id))) {
			return true;
		}
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}
	
	public Vector<String> PacienteMedico(String NombreID) {
		String id=null;
		String name=null;
		String identificador=null;
		Vector <String> PacienteMedico = new Vector<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.Nombre,Paciente.DNI,Paciente.ID from Paciente join Medico on Paciente.MedicoID=Medico.ID where Medico.NombreID like"+"'"+NombreID+"'" );

			while ( rs.next() ) {
					identificador =rs.getString("ID");
					name = rs.getString("Nombre");
					id=rs.getString("DNI");
					String suma=name+" "+id+" "+identificador;
				    PacienteMedico.add(suma);
						 
		}
			
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return AlgoritmosTPA.bubble(PacienteMedico);
	}
	
	public String ConsultaPacienteNombre(int identificador) {
		String name=null;
		PacienteActual=identificador;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.Nombre from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				name = rs.getString("Nombre");

				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return name;
	}
	public String ConsultaPacienteDNI(int identificador) {
		String DNI=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.DNI from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				DNI = rs.getString("DNI");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return DNI;
	}
	public String ConsultaPacienteDireccion(int identificador) {
		String Direccion=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.Direccion from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				Direccion = rs.getString("Direccion");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return Direccion;
	}
	public String ConsultaPacienteSS(int identificador) {
		String SeguridadSocial=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.SeguridadSocial from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				SeguridadSocial = rs.getString("SeguridadSocial");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return SeguridadSocial;
	}
	public String ConsultaPacienteEstado(int identificador) {
		String Estado=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.Estado from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				Estado = rs.getString("Estado");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return Estado;
	}
	public String ConsultaPacienteSexo(int identificador) {
		String Sexo=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Paciente.Sexo from Paciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				Sexo = rs.getString("Sexo");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return Sexo;
	}
	public String ECG(int identificador) {
		String ECG=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select PacienteECG.ECG from PacienteECG join Paciente on Paciente.ID=PacienteECG.IDPaciente where Paciente.ID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				ECG = rs.getString("ECG");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ECG;
	}
	public int ECGCount() {
		int ECG=0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select count(ECG.NombreID) from ECG" );

			while ( rs.next() ) {
				ECG = rs.getInt("count(ECG.NombreID)");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ECG;
	}
	public String ECGPuntos(String identificador) {
		String ECG=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select ecg.Puntos from ECG where ecg.NombreID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				ECG = rs.getString("Puntos");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ECG;
	}
	public int ECGFrecuencia(String identificador) {
		int Frecuencia=0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select ecg.Frecuencia from ECG where ecg.NombreID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				Frecuencia = rs.getInt("Frecuencia");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return Frecuencia;
	}
	public String ECGInforme(String identificador) {
		String Informe=null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select ecg.Informe from ECG where ecg.NombreID="+"'"+identificador+"'" );

			while ( rs.next() ) {
				Informe = rs.getString("Informe");
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return Informe;
	}
	
	public Vector<String> ConsultaMedicosAdmin() {
		
		String name=null;
		Vector <String> ConsultaMedicosAdmin = new Vector<String>();
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Medico.Nombre from Medico order by Medico.Nombre asc ");

			while ( rs.next() ) {
			
				name = rs.getString("Nombre");
				ConsultaMedicosAdmin.add(name);
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ConsultaMedicosAdmin;
	}
	
public  Vector<String> ConsultaTecnicosAdmin() {
		
		String name=null;
		
		Vector <String> ConsultaTecnicosAdmin = new Vector<String>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
			c.setAutoCommit(false);
			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select Tecnico.Nombre from Tecnico order by Tecnico.Nombre asc ");

			while ( rs.next() ) {
			
				name = rs.getString("Nombre");
				ConsultaTecnicosAdmin.add(name);
				
				
		}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
			return ConsultaTecnicosAdmin;
	}

public int MedicoID(String identificador) {
	int MedicoID=0;
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "select Medico.ID from Medico where Medico.NombreID="+"'"+identificador+"'");
		while ( rs.next() ) {
			MedicoID = rs.getInt("ID");
			
	}
		
		rs.close();
		stmt.close();
		c.close();
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
		return MedicoID;
}
public ResultSet Buscador(String identificador, int MedicoActual) {
	String buscar=null;
	ResultSet rs = null;
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		
		stmt = c.createStatement();
		 rs = stmt.executeQuery( "select Paciente.ID,Paciente.Nombre,Paciente.DNI from Paciente join Medico on Medico.ID=Paciente.MedicoID where(Medico.ID="+MedicoActual+") and Paciente.Nombre like "+"'"+identificador+"%'");
		 
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
		return rs;
}
public ResultSet BuscadorTecnico(String identificador) {
	String buscar=null;
	ResultSet rs = null;
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		
		stmt = c.createStatement();
		 rs = stmt.executeQuery( "select Paciente.ID,Paciente.Nombre from Paciente where Paciente.Nombre like "+"'"+identificador+"%'");

	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
		return rs;
}

public Vector<String> PacienteRandom() {
	String id = null;
	String name=null;
	ResultSet rs = null;
	Vector <String> PacienteTecnico = new Vector<String>();
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		stmt = c.createStatement();
		int random2=(int) (Math.random()*12)+2;
		int contador=0;
		while(contador<random2) {
		int random=(int) (Math.random()*23)+1;
		 rs = stmt.executeQuery( "select Paciente.ID,Paciente.Nombre from Paciente where Paciente.ID="+random);
		contador++;
		

		while ( rs.next() ) {
				name = rs.getString("Nombre");
				id = rs.getString("ID");
				String sum = name + " " +id;
				PacienteTecnico.add(sum);
	}
		}
		rs.close();
		stmt.close();
		c.close();
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
	return AlgoritmosTPA.bubble(PacienteTecnico);
}
public Vector<String> PacienteTecnico() {
	String id = null;
	String name=null;
	ResultSet rs = null;
	Vector <String> PacienteTecnico = new Vector<String>();

	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		stmt = c.createStatement();
		 rs = stmt.executeQuery( "select Paciente.ID,Paciente.Nombre from Paciente ");

		while ( rs.next() ) {
				name = rs.getString("Nombre");
				id = rs.getString("ID");
				String sum = name + " " +id;
				
				PacienteTecnico.add(sum);
	}
		
		rs.close();
		stmt.close();
		c.close();
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
	return AlgoritmosTPA.bubble(PacienteTecnico);
}


public String VerificarDNI(String identificador) {
	String DNI=null;
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
		
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "select Paciente.DNI from Paciente where Paciente.ID like"+"'"+identificador+"'" );

		while ( rs.next() ) {
			DNI = rs.getString("DNI");
	}
		rs.close();
		stmt.close();
		c.close();
	} catch ( Exception e ) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	}
		return DNI;
}

public Image getPhotoHombre() {
    ResultSet rs = null;
    Statement stmt = null;
    Image image = null;

    try {

    	Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
        String sqlQ = "SELECT Fotos.Hombre FROM Fotos";
        stmt = c.createStatement();
        rs = stmt.executeQuery(sqlQ);

    rs.next();
            
            InputStream is = rs.getBinaryStream("Hombre");
            image = ImageIO.read(is);
        
        rs.close();
        stmt.close();
        c.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {           
        return image;
    }
}

public Image getPhotoMujer() {
    ResultSet rs = null;
    Statement stmt = null;
    Image image = null;

    try {

    	Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
        String sqlQ = "SELECT Fotos.Mujer FROM Fotos";
        stmt = c.createStatement();
        rs = stmt.executeQuery(sqlQ);

    rs.next();
            
            InputStream is = rs.getBinaryStream("Mujer");
            image = ImageIO.read(is);
        
        rs.close();
        stmt.close();
        c.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {           
        return image;
    }
}

public Image getPhotoLogo() {
    ResultSet rs = null;
    Statement stmt = null;
    Image image = null;

    try {

    	Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
        String sqlQ = "SELECT Fotos.Logo FROM Fotos";
        stmt = c.createStatement();
        rs = stmt.executeQuery(sqlQ);

    rs.next();
            
            InputStream is = rs.getBinaryStream("Logo");
            image = ImageIO.read(is);
        
        rs.close();
        stmt.close();
        c.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {           
        return image;
    }
}
public Image getPhotoLogoVentanas() {
    ResultSet rs = null;
    Statement stmt = null;
    Image image = null;

    try {

    	Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mariadb://esp.uem.es:3306/pi2_bd_easycardio","easycardio", "easycardio");
		c.setAutoCommit(false);
		
        String sqlQ = "SELECT Fotos.LogoVentanas FROM Fotos";
        stmt = c.createStatement();
        rs = stmt.executeQuery(sqlQ);

    rs.next();
            
            InputStream is = rs.getBinaryStream("LogoVentanas");
            image = ImageIO.read(is);
        
        rs.close();
        stmt.close();
        c.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {           
        return image;
    }
}
}


