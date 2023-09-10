package instrumentos.presentation.Calibraciones;

import instrumentos.Application;
import instrumentos.logic.Instrumento;
import instrumentos.logic.Service;
import instrumentos.logic.Calibraciones;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Calibraciones()));
        this.view = view;
        this.model = model;
        this.shown();
        view.setController(this);
        view.setModel(model);
    }

    public void search(Calibraciones filter) throws Exception {
        List<Calibraciones> rows = Service.instance().search(filter);
        if (rows.isEmpty()) {
            throw new Exception("NINGUN REGISTRO COINCIDE");
        }
        model.setList(rows);
        model.setCurrent(rows.get(0));
        model.commit();
    }

    public void edit(int row) throws Exception {     //cambiado
        model.setMode(Application.MODE_EDIT);
        Calibraciones e = model.getList().get(row);
        model.setCurrent(e);
        model.commit();
    }

    public void save(Calibraciones e) throws Exception {
        if (model.getInstrumento().getSerie() == "") {
            throw new Exception("No hay instrumento seleccionado");
        }
        if (model.getMode() == 1) {
            Service.instance().create(e);
            //model.getInstrumento().getCalibraciones().add(e);
            this.search(new Calibraciones());
        }
        if (model.getMode() == 2) {
            Service.instance().update(e);
            this.search(new Calibraciones());
        }
    }

    public void del(int row) throws Exception {

        Calibraciones e = model.getList().get(row);
        // Realiza la eliminación en el servicio (void)
        Service.instance().delete(e);

        // Verifica si el elemento se ha eliminado correctamente en el modelo local
        if (model.getList().remove(e)) {
            updateNumerosSecuenciales();
            // Actualiza la vista con la lista modificada
            int[] cols = {TableModel.NUMERO, TableModel.FECHA, TableModel.MEDICIONES};
            view.getList().setModel(new TableModel(cols, model.getList()));
        } else {
            throw new Exception("Error al eliminar el elemento...");
        }

    }

    private void updateNumerosSecuenciales() {
        List<Calibraciones> calibracionesList = model.getList();
        for (int i = 0; i < calibracionesList.size(); i++) {
            Calibraciones calibracion = calibracionesList.get(i);
            calibracion.setNumero(i + 1); // Actualiza el número secuencial
        }
    }

    public String shown() {
        model.setInstrumento(Calibraciones.getInstrumento());

        String textoInstrumento;
        if (!model.getInstrumento().getSerie().isEmpty()) {
            textoInstrumento = model.getInstrumento().getSerie() + " - " + model.getInstrumento().getDescripcion() + "(" + model.getInstrumento().getMinimo() + "-" + model.getInstrumento().getMaximo() + " Grados Celsius)";

            // Filtra las calibraciones del instrumento actual
            List<Calibraciones> calibracionesDelInstrumento = model.getInstrumento().getCalibraciones();

            // Actualiza la tabla con la lista filtrada de calibraciones
            int[] cols = {TableModel.NUMERO, TableModel.FECHA, TableModel.MEDICIONES};
            view.getList().setModel(new TableModel(cols, calibracionesDelInstrumento));
        } else {
            textoInstrumento = "Ningún instrumento seleccionado.";
        }
        return textoInstrumento;
    }


}