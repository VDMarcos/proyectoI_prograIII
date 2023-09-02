package instrumentos.presentation.tipos;

import instrumentos.Application;
import instrumentos.logic.Service;
import instrumentos.logic.TipoInstrumento;

import java.util.List;

public class Controller{
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new TipoInstrumento()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(TipoInstrumento filter) throws  Exception{
        List<TipoInstrumento> rows = Service.instance().search(filter);
        if (rows.isEmpty()){
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        //model.setCurrent(new TipoInstrumento());
        model.setCurrent(rows.get(0));
        model.commit();
    }

    public void edit(int row){
        TipoInstrumento e = model.getList().get(row);
        try {
            model.setCurrent(Service.instance().read(e));
            model.commit();
        } catch (Exception ex) {}
    }

    public void save(TipoInstrumento e) throws Exception {
        if(model.mode==1){
                Service.instance().create(e);
                this.search(new TipoInstrumento());
        }
        if(model.mode==2){

        }
    }
}
