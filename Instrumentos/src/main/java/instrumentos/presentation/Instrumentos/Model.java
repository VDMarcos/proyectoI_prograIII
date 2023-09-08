package instrumentos.presentation.Instrumentos;

import instrumentos.logic.Instrumentos;
import instrumentos.logic.TipoInstrumento;

import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    List<Instrumentos> list;
    Instrumentos current;

    List<TipoInstrumento> listTypes;
    int mode;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    int changedProps = NONE;

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(changedProps);
        changedProps = NONE;
    }

    public Model() {
    }

    public void init(List<Instrumentos> list){
        setList(list);
        setCurrent(new Instrumentos());
    }

    public List<Instrumentos> getList() {
        return list;
    }

    public List<TipoInstrumento> getListTypes() {
        return listTypes;
    }

    public void setListTypes(List<TipoInstrumento> listTypes) {
        this.listTypes = listTypes;
        changedProps += TYPES;
    }

    public void setList(List<Instrumentos> list){
        this.list = list;
        changedProps +=LIST;
    }

    public Instrumentos getCurrent() {
        return current;
    }
    public void setCurrent(Instrumentos current) {
        changedProps +=CURRENT;
        this.current = current;
    }

    public static int NONE=0;
    public static int LIST=1;
    public static int CURRENT=2;

    public static  int TYPES=4;
}
