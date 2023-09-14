package instrumentos.logic;

import java.util.Objects;

public class Calibraciones {

    static Instrumento instrumento;     //static???

    String fecha;

    int mediciones;

    int numero;
    public Calibraciones() {
       this(new Instrumento(), "", 0);
    }


    public Calibraciones(Instrumento inst, String fecha, int mediciones) {
        this.instrumento = inst;
        this.fecha = fecha;
        this.mediciones = mediciones;

        // Establece el valor de numero siempre en 0
        this.numero = 0;

        //if (mediciones != 0) {
           // this.numero = 0;
        //}
    }

    public static Instrumento getInstrumento() {
        return instrumento;
    }

    public static void setInstrumento(Instrumento instrumen) {
        instrumento = instrumen;
    }

    //public void disminuirCantidad(){
      //  ultiNumero--;
    //}

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int nu) { this.numero = nu; }

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


