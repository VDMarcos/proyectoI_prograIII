package instrumentos.data;

import instrumentos.logic.Calibraciones;
import instrumentos.logic.Instrumento;
import instrumentos.logic.TipoInstrumento;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElementWrapper(name = "tipos")
    @XmlElement(name = "tipo")
    private List<TipoInstrumento> tipos;

    public List<Calibraciones> getCalibraciones() {
        return calibraciones;
    }

    private List<Calibraciones> calibraciones;
    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    private List<Instrumento> instrumentos;

    public Data() {
        tipos = new ArrayList<>();
        instrumentos = new ArrayList<>();
        calibraciones = new ArrayList<>();

        //tipos.add(new TipoInstrumento("TER","Termómetro","Grados Celcius") );
        //tipos.add(new TipoInstrumento("BAR","Barómetro","PSI") );
        //tipos.add(new TipoInstrumento("BAL","Balanza","Gramos") );
        //tipos.add(new TipoInstrumento("VOL","Voltimetro","Voltios") );
    }

    public List<TipoInstrumento> getTipos() {
        return tipos;
    }
 }
//