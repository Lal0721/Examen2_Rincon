package co.edu.poli.examen2_Rincon.modelo;

import java.time.LocalDate;

public class SeguroDeVida extends Seguro {
    private String beneficiario;

    public SeguroDeVida(int numero, LocalDate fechaExpedicion, String estado, Asegurado asegurado, String beneficiario) {
        super(numero, fechaExpedicion, estado, asegurado);
        this.beneficiario = beneficiario;
    }

    public String getBeneficiario() { return beneficiario; }

    @Override
    public String toString() {
        return "VIDA: " + super.toString() + " | Beneficiario: " + beneficiario;
    }
}