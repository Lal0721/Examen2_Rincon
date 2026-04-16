package co.edu.poli.examen2_Rincon.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	// 'static' permite que la instancia sea única en todo el programa (Singleton)
	private static ConexionBD instancia;
	private Connection conexion;

	// El constructor es 'private' para que nadie más pueda crear conexiones nuevas con 'new'
	private ConexionBD() throws Exception {
		// Dirección de la base de datos (IP, puerto y nombre de la DB)
		String url = "jdbc:mysql://127.0.0.1:3306/Examen2_Rincon";
		String user = "camilo";
		String pass = "Admin123*";

		try {
			// Busca el conector de MySQL (el Driver) en las librerías del proyecto
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Intenta establecer el puente de comunicación
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			throw new Exception("Error: No se encontró el Driver de MySQL. Verifica el JAR.");
		} catch (SQLException e) {
			throw new Exception("Error de SQL: " + e.getMessage());
		}
	}

	// Método global para obtener la única conexión existente
	public static ConexionBD getInstancia() throws Exception {
		// Si es la primera vez, crea la conexión. Si ya existe, devuelve la que ya tiene.
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	// Devuelve el objeto Connection para que el DAO pueda hacer consultas
	public Connection getConexion() throws Exception {
		// Verifica que la conexión siga viva antes de entregarla
		if (conexion == null || conexion.isClosed()) {
			instancia = new ConexionBD();
			return instancia.conexion;
		}
		return conexion;
	}
}
