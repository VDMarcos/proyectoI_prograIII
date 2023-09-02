package instrumentos.presentation.tipos;

import instrumentos.Application;
import instrumentos.logic.TipoInstrumento;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JTextField searchNombre;
    private JButton search;
    private JButton save;
    private JTable list;
    private JButton delete;
    private JLabel searchNombreLbl;
    private JButton report;
    private JTextField codigo;
    private JTextField nombre;
    private JTextField unidad;
    private JLabel codigoLbl;
    private JLabel nombreLbl;
    private JLabel unidadLbl;
    private JButton clear;

    public View() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TipoInstrumento filter= new TipoInstrumento();
                    filter.setNombre(searchNombre.getText());
                    model.mode = 1;
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                model.mode =2;
                controller.edit(row);
                codigo.setEnabled(false);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.mode =1;
                TipoInstrumento filter= new TipoInstrumento();
                filter.setNombre(nombre.getText());
                filter.setCodigo(codigo.getText());
                filter.setUnidad(unidad.getText());
                try {
                    controller.save(filter);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object properties) {
            int changedProps = (int) properties;
            if ((changedProps & Model.LIST) == Model.LIST) {
                int[] cols = {TableModel.CODIGO, TableModel.NOMBRE, TableModel.UNIDAD};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(2).setPreferredWidth(200);
                list.setRowSelectionInterval(0, 0);
            }
            if ((changedProps & Model.CURRENT) == Model.CURRENT) {
                codigo.setText(model.getCurrent().getCodigo());
                nombre.setText(model.getCurrent().getNombre());
                unidad.setText(model.getCurrent().getUnidad());
            }
            this.panel.revalidate();
    }
}

   // public void update(Observable updatedModel, Object properties) {
      //  int changedProps = (int) properties;
        //if ((changedProps & Model.LIST) == Model.LIST) {
          //  int[] cols = {TableModel.CODIGO,TableModel.NOMBRE, TableModel.UNIDAD};
          //  list.setModel(new TableModel(cols, model.getList()));
            //list.setRowHeight(30);
            //TableColumnModel columnModel = list.getColumnModel();
            //columnModel.getColumn(2).setPreferredWidth(200);
//list.setRowSelectionInerval(0,0);
        //}
        //if ((changedProps & Model.CURRENT) == Model.CURRENT) {
          //  codigo.setText(model.getCurrent().getCodigo());
            //nombre.setText(model.getCurrent().getNombre());
            //unidad.setText(model.getCurrent().getUnidad());
       // }
        //this.panel.revalidate();
    //}

//public void update(Observable updatedModel, Object properties) {
//int changedProps = (int) properties;
// if ((changedProps & Model.LIST) == Model.LIST) {
//   int[] cols = {TableModel.NOMBRE, TableModel.UNIDAD};
//           list.setModel(new TableModel(cols, model.getList()));
//  list.setRowHeight(30);
//  TableColumnModel columnModel = list.getColumnModel();
//  columnModel.getColumn(1).setPreferredWidth(200);
// }
// if ((changedProps & Model.CURRENT) == Model.CURRENT) {
//      nombre.setText(model.getCurrent().getNombre());
//     unidad.setText(model.getCurrent().getUnidad());
//  }
//  this.panel.revalidate();
// }