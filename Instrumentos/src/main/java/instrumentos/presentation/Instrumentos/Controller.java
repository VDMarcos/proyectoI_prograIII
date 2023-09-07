package instrumentos.presentation.Instrumentos;

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
        model.setCurrent(rows.get(0));
        model.commit();
    }

    public void edit(int row) throws Exception{
        TipoInstrumento e = model.getList().get(row);
        model.setCurrent(Service.instance().read(e));
        model.commit();
    }

    public void save(TipoInstrumento e) throws Exception {
        if (model.mode == 1) {
            Service.instance().create(e);
            this.search(new TipoInstrumento());
        }
        if(model.mode==2) {
            Service.instance().update(e);
            this.search(new TipoInstrumento());
        }
    }

    public void del(int row) throws Exception{

        TipoInstrumento e = model.getList().get(row);
        // Realiza la eliminación en el servicio (void)
        Service.instance().delete(e);

        // Verifica si el elemento se ha eliminado correctamente en el modelo local
        if (model.getList().remove(e)) {
            // Actualiza la vista con la lista modificada
            int[] cols = {TableModel.CODIGO, TableModel.NOMBRE, TableModel.UNIDAD};
            view.getList().setModel(new TableModel(cols, model.getList()));
        } else {
            throw new Exception("Error al eliminar el elemento...");
        }

    }
}