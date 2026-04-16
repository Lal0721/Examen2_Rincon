package co.edu.poli.examen2_Rincon.servicios;

import java.sql.*;
import java.util.List;
import co.edu.poli.examen2_Rincon.modelo.*;

public class DAOSeguro implements CRUD<Seguro> {

    @Override
    public String create(Seguro t) throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        // Desactivamos AutoCommit para manejar una transacción (ambas tablas o nada)
        con.setAutoCommit(false); 

        try {
            // 1. Inserción en tabla PADRE 'seguro'
            String sqlPadre = "INSERT INTO seguro (numero, fecha_expedicion, estado, asegurado_id) VALUES (?, ?, ?, ?)";
            PreparedStatement psPadre = con.prepareStatement(sqlPadre);
            psPadre.setInt(1, t.getNumero());
            psPadre.setDate(2, Date.valueOf(t.getFechaExpedicion()));
            psPadre.setString(3, t.getEstado());
            psPadre.setString(4, t.getAsegurado().getId());
            psPadre.executeUpdate();

            // 2. Inserción en tabla HIJA según el tipo de objeto (Polimorfismo)
            String sqlHija = "";
            PreparedStatement psHija;
            if (t instanceof SeguroDeVida) {
                sqlHija = "INSERT INTO segurovida (numero, beneficiario) VALUES (?, ?)";
                psHija = con.prepareStatement(sqlHija);
                psHija.setInt(1, t.getNumero());
                psHija.setString(2, ((SeguroDeVida) t).getBeneficiario());
            } else {
                sqlHija = "INSERT INTO segurovehiculo (numero, marca) VALUES (?, ?)";
                psHija = con.prepareStatement(sqlHija);
                psHija.setInt(1, t.getNumero());
                psHija.setString(2, ((SeguroDeVehiculo) t).getMarca());
            }
            psHija.executeUpdate();

            // Confirmamos la operación en ambas tablas
            con.commit(); 
            return "✔ Seguro guardado exitosamente en el sistema.";

        } catch (SQLException e) {
            // Si hay error (ej: falta la fecha), deshacemos todo para evitar datos incompletos
            con.rollback(); 
            return "Error al guardar: " + e.getMessage();
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public <K> Seguro readone(K id) throws Exception {
        Connection con = ConexionBD.getInstancia().getConexion();
        int num = Integer.parseInt(id.toString());

        // Consulta con JOIN para reconstruir el objeto SeguroDeVida con su Asegurado
        String sqlVida = "SELECT s.*, v.beneficiario, a.nombre FROM seguro s " +
                         "JOIN segurovida v ON s.numero = v.numero " +
                         "JOIN asegurado a ON s.asegurado_id = a.id WHERE s.numero = ?";
        
        PreparedStatement ps = con.prepareStatement(sqlVida);
        ps.setInt(1, num);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Asegurado as = new Asegurado(rs.getString("asegurado_id"), rs.getString("nombre"));
            return new SeguroDeVida(num, rs.getDate("fecha_expedicion").toLocalDate(), 
                                   rs.getString("estado"), as, rs.getString("beneficiario"));
        }

        // Consulta con JOIN para SeguroDeVehiculo
        String sqlVehiculo = "SELECT s.*, v.marca, a.nombre FROM seguro s " +
                             "JOIN segurovehiculo v ON s.numero = v.numero " +
                             "JOIN asegurado a ON s.asegurado_id = a.id WHERE s.numero = ?";
        
        ps = con.prepareStatement(sqlVehiculo);
        ps.setInt(1, num);
        rs = ps.executeQuery();

        if (rs.next()) {
            Asegurado as = new Asegurado(rs.getString("asegurado_id"), rs.getString("nombre"));
            return new SeguroDeVehiculo(num, rs.getDate("fecha_expedicion").toLocalDate(), 
                                       rs.getString("estado"), as, rs.getString("marca"));
        }
        return null;
    }

    @Override
    public List<Seguro> readall() throws Exception { return null; }
}