package co.edu.poli.examen2_Rincon.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Rincon.modelo.*;
import co.edu.poli.examen2_Rincon.servicios.DAOSeguro;
import java.time.LocalDate;

class TestDAOSeguro { 

    @Test
    void testCrearSeguroVida() { // <--- Aquí añadimos el nombre 'testCrearSeguroVida'
        DAOSeguro dao = new DAOSeguro();
        
        // Creamos el asegurado y el seguro de vida para la prueba
        Asegurado as = new Asegurado("123", "Rincon Test");
        SeguroDeVida sv = new SeguroDeVida(999, LocalDate.now(), "Activo", as, "Familiar");
        
        try {
            String res = dao.create(sv);
            assertNotNull(res);
            // Verificamos que el mensaje de retorno confirme el éxito
            assertTrue(res.toLowerCase().contains("exito") || res.toLowerCase().contains("éxito"));
        } catch (Exception e) {
            fail("El test falló debido a una excepción: " + e.getMessage());
        }
    }
}