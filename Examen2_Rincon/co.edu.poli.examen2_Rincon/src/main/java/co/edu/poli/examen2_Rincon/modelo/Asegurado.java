package co.edu.poli.examen2_Rincon.modelo;

/**
 * Clase que representa la entidad 'asegurado' de la base de datos.
 * Es un POJO (Plain Old Java Object) que transporta los datos entre el DAO y la Vista.
 */
public class Asegurado {
    
    // Atributos que coinciden con las columnas 'id' y 'nombre' en MySQL
    private String id;
    private String nombre;

    /**
     * Constructor para inicializar un asegurado.
     * Se usa en el DAOAsegurado al leer los registros con rs.getString().
     */
    public Asegurado(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos Getter: Permiten al DAO y al Controlador acceder a la información
    public String getId() { return id; }
    public String getNombre() { return nombre; }

    /**
     * MÉTODO CRÍTICO: El ComboBox de JavaFX utiliza el método toString() 
     * para saber qué texto mostrar en la lista desplegable.
     * Al retornar el nombre, evitamos que se vean códigos extraños en la interfaz.
     */
    @Override
    public String toString() {
        // Mostramos el nombre y el ID para que el usuario identifique bien al asegurado
        return nombre + " (" + id + ")";
    }
}