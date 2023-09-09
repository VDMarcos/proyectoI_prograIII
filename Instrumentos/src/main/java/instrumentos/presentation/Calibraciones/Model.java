package instrumentos.presentation.Calibraciones;

import instrumentos.Application;
import instrumentos.logic.Calibraciones;

import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    List<Calibraciones> list;
    Calibraciones current;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = Application.MODE_CREATE;
    }

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

    public void init(List<Calibraciones> list){
        setList(list);
        setCurrent(new Calibraciones());
    }

    public List<Calibraciones> getList() {
        return list;
    }
    public void setList(List<Calibraciones> list){
        this.list = list;
        changedProps +=LIST;
    }

    public Calibraciones getCurrent() {
        return current;
    }
    public void setCurrent(Calibraciones current) {
        changedProps +=CURRENT;
        this.current = current;
    }

    public static int NONE=0;
    public static int LIST=1;
    public static int CURRENT=2;
}
