package co.edu.poli.examen2_Rincon.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            System.out.println("Iniciando carga de recursos para Rincon...");

         // La ruta debe empezar con / y seguir la estructura de carpetas de resources
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/poli/examen2_Rincon/formCard.fxml"));            
            if (fxmlLoader.getLocation() == null) {
                throw new Exception("No se encontró el archivo FXML en la ruta de Rincon. Verifica la carpeta resources.");
            }

            // 2. Cargar el diseño
            Parent root = fxmlLoader.load();
            
            // 3. Crear la escena
            Scene scene = new Scene(root);
            
            // 4. Configurar el título de la ventana con tu nombre
            System.out.println("Configurando ventana principal...");
            stage.setTitle("Sistema de Gestión de Seguros - Rincon"); 
            stage.setScene(scene);
            stage.setResizable(false); 
            
            // 5. Mostrar ventana
            stage.show();
            
            System.out.println("¡Aplicación de Rincon cargada con éxito!");

        } catch (Exception e) {
            System.err.println("--- ERROR CRÍTICO AL INICIAR LA APLICACIÓN ---");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
