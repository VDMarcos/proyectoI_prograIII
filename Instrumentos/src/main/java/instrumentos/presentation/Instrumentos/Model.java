package instrumentos.presentation.Instrumentos;

import instrumentos.logic.Instrumentos;

import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    List<Instrumentos> list;
    Instrumentos current;

    int mode;
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
}
