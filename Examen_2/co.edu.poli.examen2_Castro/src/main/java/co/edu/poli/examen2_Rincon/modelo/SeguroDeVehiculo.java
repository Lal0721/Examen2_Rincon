package co.edu.poli.examen2_Rincon.modelo;

import java.time.LocalDate;

public class SeguroDeVehiculo extends Seguro {
    private String marca;

    public SeguroDeVehiculo(int numero, LocalDate fechaExpedicion, String estado, Asegurado asegurado, String marca) {
        super(numero, fechaExpedicion, estado, asegurado);
        this.marca = marca;
    }

    public String getMarca() { return marca; }

    @Override
    public String toString() {
        return "VEHÍCULO: " + super.toString() + " | Marca: " + marca;
    }
}