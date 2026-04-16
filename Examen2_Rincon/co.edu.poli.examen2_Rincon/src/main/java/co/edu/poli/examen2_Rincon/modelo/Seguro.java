package co.edu.poli.examen2_Rincon.modelo;

import java.time.LocalDate;

public abstract class Seguro {
    private int numero;
    private LocalDate fechaExpedicion;
    private String estado;
    private Asegurado asegurado;

    public Seguro(int numero, LocalDate fechaExpedicion, String estado, Asegurado asegurado) {
        this.numero = numero;
        this.fechaExpedicion = fechaExpedicion;
        this.estado = estado;
        this.asegurado = asegurado;
    }

    public int getNumero() { return numero; }
    public LocalDate getFechaExpedicion() { return fechaExpedicion; }
    public String getEstado() { return estado; }
    public Asegurado getAsegurado() { return asegurado; }

    @Override
    public String toString() {
        return "Seguro #" + numero + " [" + estado + "] - Asegurado: " + asegurado.getNombre();
    }
}