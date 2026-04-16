package co.edu.poli.examen2_Rincon.servicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.examen2_Rincon.modelo.Asegurado;

/**
 * Implementación del CRUD para la tabla 'asegurado'.
 * Conecta la lógica de Java con MySQL Workbench.
 */
public class DAOAsegurado implements CRUD<Asegurado> {

    @Override
    public String create(Asegurado t) throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        // Inserta un nuevo asegurado usando sentencias preparadas (seguridad)
        String sql = "INSERT INTO asegurado (id, nombre) VALUES (?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getId());
            ps.setString(2, t.getNombre());
            ps.executeUpdate();
            return "✔ Asegurado registrado con éxito.";
        } catch (SQLException e) {
            return "Error SQL: " + e.getMessage();
        }
    }

    @Override
    public List<Asegurado> readall() throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        List<Asegurado> lista = new ArrayList<>();
        // Consulta todos los registros para llenar el ComboBox en la interfaz
        String sql = "SELECT * FROM asegurado";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // Mapea cada fila de la tabla a un objeto de la clase Asegurado
                lista.add(new Asegurado(
                    rs.getString("id"), 
                    rs.getString("nombre")
                ));
            }
        }
        return lista;
    }

    @Override
    public <K> Asegurado readone(K id) throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        // Busca un asegurado específico por su llave primaria (ID)
        String sql = "SELECT * FROM asegurado WHERE id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Asegurado(rs.getString("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }
}