package co.edu.poli.examen2_Rincon.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import co.edu.poli.examen2_Rincon.modelo.Asegurado;
import co.edu.poli.examen2_Rincon.servicios.DAOAsegurado;

public class TestDAOAsegurado {

    DAOAsegurado dao = new DAOAsegurado();

    @Test
    void testFlujoAsegurado() throws Exception {
        // 1. Crear un Asegurado de prueba
        Asegurado nuevo = new Asegurado("1011", "David Camacho");

        // 2. Probar creación
        String resultado = dao.create(nuevo);
        System.out.println(resultado);
        assertTrue(resultado.contains("éxito") || resultado.contains("exito"));

        // 3. Probar lectura general
        List<Asegurado> lista = dao.readall();
        assertNotNull(lista);
        assertFalse(lista.isEmpty(), "La lista no debería estar vacía tras la inserción");
    }
}