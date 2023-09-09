package instrumentos.logic;

import java.util.Objects;

public class Calibraciones {

    Instrumento instrumento;

    String fecha;

    int numero;

    int mediciones;

    public Calibraciones() {
        this(new Instrumento(), "", 0, 0);
    }

    public Calibraciones(Instrumento inst, String fecha, int numero, int mediciones) {
        this.instrumento = inst;
        this.fecha = fecha;
        this.numero = numero;
        this.mediciones = mediciones;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMediciones() {
        return mediciones;
    }

    public void setMediciones(int mediciones) {
        this.mediciones = mediciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calibraciones that = (Calibraciones) o;
        return numero == that.numero && mediciones == that.mediciones && Objects.equals(instrumento, that.instrumento) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumento, fecha, numero, mediciones);
    }
}
