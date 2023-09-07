package instrumentos.presentation.Instrumentos;

import instrumentos.logic.Instrumentos;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {
    private JPanel panel;
    private JTextField searchNombre;
    private JButton search;
    private JButton save;

    public JTable getList() {
        return list;
    }

    private JTable list;
    private JButton delete;
    private JLabel searchNombreLbl;
    private JButton report;
    private JTextField serie;
    private JTextField minimo;
    private JTextField descripcion;
    private JLabel serieLbl;
    private JLabel minimoLbl;
    private JLabel descripcionLbl;
    private JButton clear;
    private JLabel toleranciaLbl;
    private JTextField tolerancia;
    private JLabel maximoLbl;
    private JTextField maximo;

    public View() {
        delete.setEnabled(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Instrumentos filter = new Instrumentos();
                    filter.setNombre(searchNombre.getText());
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
                model.mode = 2;
                try {
                    controller.edit(row);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                serie.setEnabled(false);
                delete.setEnabled(true);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Instrumentos filter = new Instrumentos();
                filter.setNombre(minimo.getText());
                filter.setCodigo(serie.getText());
                filter.setUnidad(descripcion.getText());
                try {
                    if(!isValid()){
                        throw new Exception("Campos vacios");
                    }
                    controller.save(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                clearTextFields();
            }
        });
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = list.getSelectedRow();
                try {
                    controller.del(row);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                clearTextFields();
            }
        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearTextFields();
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
        model.mode = 1;
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
            serie.setText(model.getCurrent().getCodigo());
            minimo.setText(model.getCurrent().getNombre());
            descripcion.setText(model.getCurrent().getUnidad());
        }
        this.panel.revalidate();
    }

    public void clearTextFields(){
        model.mode = 1;
        serie.setText(null);
        minimo.setText(null);
        descripcion.setText(null);
        serie.setEnabled(true);
        delete.setEnabled(false);
    }
    public boolean isValid(){
        if(serie.getText().isEmpty() || minimo.getText().isEmpty() || descripcion.getText().isEmpty()){
            return false;
        }
        return true;
    }

}

