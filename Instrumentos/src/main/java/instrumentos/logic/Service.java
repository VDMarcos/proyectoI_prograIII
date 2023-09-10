package instrumentos.logic;

import instrumentos.data.Data;
import instrumentos.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){
        try{
            data = XmlPersister.instance().load();
        }
        catch (Exception e){
            data = new Data();
        }

    }

    public void stop(){
        try{
            XmlPersister.instance().store(data);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //================= TIPOS DE INSTRUMENTO ============
    public void create(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTipos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getTipos().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public TipoInstrumento read(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTipos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(TipoInstrumento e) throws Exception{
        TipoInstrumento result = null;
        try{
            result = this.read(e);
            data.getTipos().remove(result);
            data.getTipos().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(TipoInstrumento e) throws Exception{
        data.getTipos().remove(e);
     }

    public List<TipoInstrumento> search(TipoInstrumento e){
        return data.getTipos().stream()
                .filter(i->i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(TipoInstrumento::getNombre))  // lo cambiamos a ::getUNidad en clase, pero estaba en ::getNombre...
                .collect(Collectors.toList());
    }

    //================= INSTRUMENTOS ============

    public void create(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumentos().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result==null) data.getInstrumentos().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public Instrumento read(Instrumento e) throws Exception{
        Instrumento result = data.getInstrumentos().stream()
                .filter(i->i.getSerie().equals(e.getSerie())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(Instrumento e) throws Exception{
        Instrumento result = null;
        try{
            result = this.read(e);
            data.getInstrumentos().remove(result);
            data.getInstrumentos().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(Instrumento e) throws Exception{
        data.getInstrumentos().remove(e);
    }

    public List<Instrumento> search(Instrumento e){
        return data.getInstrumentos().stream()
                .filter(i->i.getSerie().contains(e.getSerie()))
                .sorted(Comparator.comparing(Instrumento::getSerie))
                .collect(Collectors.toList());
    }

    //================= Calibraciones ============

    public void create(Calibraciones e) throws Exception {
        //Calibraciones result = data.getCalibraciones().stream()
          //      .filter(i-> Objects.equals(i.getNumero(), e.getNumero())).findFirst().orElse(null);
        //if (result==null)
        data.getCalibraciones().add(e);

        //else throw new Exception("Tipo ya existe");
    }

    public Calibraciones read(Calibraciones e) throws Exception{
        Calibraciones result = data.getCalibraciones().stream()
                .filter(i->i.getNumero()==(e.getNumero())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(Calibraciones e) throws Exception{
        Calibraciones result = null;
        try{
            result = this.read(e);
            data.getCalibraciones().remove(result);
            data.getCalibraciones().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(Calibraciones e) throws Exception{
        data.getCalibraciones().remove(e);
    }

    public List<Calibraciones> search(Calibraciones e){
        return data.getCalibraciones().stream()
                .filter(i->i.getNumero()!=(e.getNumero()))
                .sorted(Comparator.comparing(Calibraciones::getNumero))  // lo cambiamos a ::getUNidad en clase, pero estaba en ::getNombre...
                .collect(Collectors.toList());
    }

 }
