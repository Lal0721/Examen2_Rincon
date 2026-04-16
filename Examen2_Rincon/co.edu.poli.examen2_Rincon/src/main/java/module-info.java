module Examen_2 { // Usa el nombre exacto del proyecto que aparece en tu Package Explorer
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    // Abrimos los paquetes para que JavaFX (especialmente el FXMLLoader) pueda acceder
    opens co.edu.poli.examen2_Rincon.vista to javafx.fxml;
    opens co.edu.poli.examen2_Rincon.controlador to javafx.fxml;
    opens co.edu.poli.examen2_Rincon.modelo to javafx.base;

    // Exportamos el paquete para que la JVM pueda ejecutar la clase App
    exports co.edu.poli.examen2_Rincon.vista;
}