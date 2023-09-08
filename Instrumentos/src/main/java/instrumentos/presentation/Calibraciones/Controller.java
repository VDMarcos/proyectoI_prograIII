package instrumentos.presentation.Calibraciones;

import instrumentos.logic.Calibraciones;
import instrumentos.logic.Service;

import java.util.List;

public class Controller{
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Calibraciones()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(Calibraciones filter) throws  Exception{
        List<Calibraciones> rows = Service.instance().search(filter);
        if (rows.isEmpty()){
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        model.setCurrent(rows.get(0));
        model.commit();
    }

    public void edit(int row) throws Exception{
        Calibraciones e = model.getList().get(row);
        model.setCurrent(Service.instance().read(e));
        model.commit();
    }

    public void save(Calibraciones e) throws Exception {
        if (model.mode == 1) {
            Service.instance().create(e);
            this.search(new Calibraciones());
        }
        if(model.mode==2) {
            Service.instance().update(e);
            this.search(new Calibraciones());
        }
    }

    public void del(int row) throws Exception{

        Calibraciones e = model.getList().get(row);
        // Realiza la eliminación en el servicio (void)
        Service.instance().delete(e);

        // Verifica si el elemento se ha eliminado correctamente en el modelo local
        if (model.getList().remove(e)) {
            // Actualiza la vista con la lista modificada
           int[] cols = {TableModel.NUMERO, TableModel.FECHA, TableModel.MEDICIONES};
           view.getList().setModel(new TableModel(cols, model.getList()));
        } else {
            throw new Exception("Error al eliminar el elemento...");
        }

    }
}