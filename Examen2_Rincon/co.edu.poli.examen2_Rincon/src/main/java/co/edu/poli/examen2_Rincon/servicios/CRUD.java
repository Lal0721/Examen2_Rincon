package co.edu.poli.examen2_Rincon.servicios;

import java.util.List;

/**
 * Interfaz genérica para estandarizar las operaciones de base de datos.
 * <T> permite usarla para cualquier clase (Seguro, Asegurado, etc).
 */
public interface CRUD<T> {

    // Guarda un objeto en MySQL y retorna mensaje de éxito/error.
    String create(T t) throws Exception;
    
    // Busca un registro único usando un ID genérico <K> (String o Int).
    <K> T readone(K id) throws Exception;
    
    // Devuelve la lista completa de registros de una tabla.
    List<T> readall() throws Exception;
}