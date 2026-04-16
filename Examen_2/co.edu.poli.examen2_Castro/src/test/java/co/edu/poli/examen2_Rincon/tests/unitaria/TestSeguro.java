package co.edu.poli.examen2_Rincon.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import co.edu.poli.examen2_Rincon.modelo.*;

public class TestSeguro {

    @Test
    void testCreacionSeguroVida() {
        // Creamos los objetos necesarios según tu nuevo modelo
        Asegurado as = new Asegurado("123", "Carlos Rincon");
        SeguroDeVida sv = new SeguroDeVida(101, LocalDate.now(), "Activo", as, "Maria Rincon");

        // Verificamos que los datos se asignen correctamente
        assertNotNull(sv);
        assertEquals("Maria Rincon", sv.getBeneficiario());
        assertEquals("Carlos Rincon", sv.getAsegurado().getNombre());
    }

    @Test
    void testCreacionSeguroVehiculo() {
        Asegurado as = new Asegurado("456", "Ana Rincon");
        SeguroDeVehiculo sc = new SeguroDeVehiculo(202, LocalDate.now(), "Activo", as, "Mazda");

        assertNotNull(sc);
        assertEquals("Mazda", sc.getMarca());
        assertEquals(202, sc.getNumero());
    }
}