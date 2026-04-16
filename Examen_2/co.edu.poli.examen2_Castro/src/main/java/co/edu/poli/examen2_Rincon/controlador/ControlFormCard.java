package co.edu.poli.examen2_Rincon.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.poli.examen2_Rincon.modelo.*;
import co.edu.poli.examen2_Rincon.servicios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class ControlFormCard implements Initializable {

    @FXML private Button bttConsulta, bttCreacion;
    @FXML private TextField txtNumero1, txtNumero2; 
    @FXML private TextArea txtAreaResultado;
    @FXML private DatePicker datepk1;
    
    // CORRECCIÓN: Se debe llamar igual que en el FXML (Línea 39 de tu XML)
    @FXML private ComboBox<Asegurado> cmbPropietario; 
    
    @FXML private RadioButton radio1, radio2; 
    @FXML private ToggleGroup tipo;

    private DAOSeguro daoSeguro = new DAOSeguro();
    private DAOAsegurado daoAsegurado = new DAOAsegurado();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datepk1.setValue(LocalDate.now());
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            // Cargamos los asegurados desde la tabla 'asegurado'
            List<Asegurado> lista = daoAsegurado.readall();
            ObservableList<Asegurado> ol = FXCollections.observableArrayList(lista);
            
            cmbPropietario.setItems(ol);

            // Esto es para que en el combo se vea el NOMBRE y no un código raro
            cmbPropietario.setConverter(new StringConverter<Asegurado>() {
                @Override
                public String toString(Asegurado a) {
                    return (a == null) ? "" : a.getNombre();
                }
                @Override
                public Asegurado fromString(String s) { return null; }
            });

        } catch (Exception e) {
            mostrarAlerta("Error al cargar asegurados: " + e.getMessage());
        }
    }

    @FXML
    private void pressConsulta(ActionEvent event) {
        txtAreaResultado.setText("");
        String input = txtNumero1.getText();
        
        if (input != null && !input.isBlank()) {
            try {
                int numBusqueda = Integer.parseInt(input.trim());
                Seguro s = daoSeguro.readone(numBusqueda);

                if (s != null)
                    txtAreaResultado.setText(s.toString());
                else
                    mostrarAlerta("No existe el seguro número: " + numBusqueda);
            } catch (Exception e) {
                mostrarAlerta("Error: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Ingrese número de seguro.");
        }
    }

    @FXML
    private void pressCreacion(ActionEvent event) {
        try {
            int numero = Integer.parseInt(txtNumero2.getText());
            LocalDate fechaExp = datepk1.getValue();
            Asegurado seleccionado = cmbPropietario.getValue();
            
            if (seleccionado == null) {
                mostrarAlerta("⚠ Seleccione un asegurado de la lista.");
                return;
            }

            Seguro nuevo;
            if (radio1.isSelected()) {
                nuevo = new SeguroDeVida(numero, fechaExp, "Activo", seleccionado, "Beneficiario Prueba");
            } else {
                nuevo = new SeguroDeVehiculo(numero, fechaExp, "Activo", seleccionado, "Marca Prueba");
            }

            String resultado = daoSeguro.create(nuevo);
            mostrarAlerta(resultado);
            
        } catch (Exception e) {
            mostrarAlerta("Error: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sistema Rincon");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}