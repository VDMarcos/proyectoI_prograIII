/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Instrumentos {

    @XmlID
    String serie;
    @XmlIDREF
    String descripcion;
    int minimo;
    int maximo;
    int tolerancia;
    TipoInstrumento tipo;
    @XmlIDREF
    @XmlElementWrapper(name = "Calibraciones")
    @XmlElement(name = "Calibracion")

    List<Calibraciones> calibraciones;

    public Instrumentos() {
        this("","", 0, 0, 0, new TipoInstrumento());
    }

    public Instrumentos(String serie, String descripcion, int minimo, int maximo, int tolerancia, TipoInstrumento tipoInstrumento) {
        this.serie = serie;
        //region Description
        this.descripcion = descripcion;
        this.minimo = minimo;
        this.maximo = maximo;
        this.tolerancia = tolerancia;
        this.tipo = tipoInstrumento;
        //endregion
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }
    
    public String toString(){
        return this.descripcion;
    }

    public TipoInstrumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInstrumento tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.serie);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instrumentos other = (Instrumentos) obj;
        if (!Objects.equals(this.serie, other.serie)) {
            return false;
        }
        return true;
    }
}
