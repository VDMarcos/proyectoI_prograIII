package instrumentos.presentation.Calibraciones;

import instrumentos.logic.Calibraciones;
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
    private JTextField searchNum;
    private JButton search;
    private JButton save;

    public JTable getList() {
        return list;
    }

    private JTable list;

    public JTable getList2() { return list2; }
    private JTable list2;
    private JButton delete;
    private JLabel searchNumLbl;
    private JButton report;
    private JTextField numero;
    private JTextField mediciones;
    private JTextField fecha;
    private JLabel numeroLbl;
    private JLabel medicionesLbl;
    private JButton clear;
    private JLabel fechaLbl;

    public View() {
        delete.setEnabled(false);

        list2.setVisible(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Calibraciones filter = new Calibraciones();
                    filter.setNumero(Integer.parseInt(numero.getText()));
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                list2.setEnabled(true);
                int row = list.getSelectedRow();
                model.mode = 2;
                try {
                    controller.edit(row);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                numero.setEnabled(false);
                delete.setEnabled(true);
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Calibraciones filter = new Calibraciones();
                try {
                    filter.setFecha(fecha.getText());
                    filter.setNumero(Integer.parseInt(numero.getText()));
                    filter.setMediciones(Integer.parseInt(mediciones.getText()));
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
            int[] cols = {TableModel.NUMERO, TableModel.FECHA, TableModel.MEDICIONES};
            list.setModel(new TableModel(cols, model.getList()));
            list.setRowHeight(30);
            TableColumnModel columnModel = list.getColumnModel();
            columnModel.getColumn(2).setPreferredWidth(200);
        }
        if ((changedProps & Model.CURRENT) == Model.CURRENT) {
            numero.setText(String.valueOf(model.getCurrent().getNumero()));
            fecha.setText(model.getCurrent().getFecha());
            try {
                numero.setText(String.valueOf(model.getCurrent().getNumero()));
                mediciones.setText(String.valueOf(model.getCurrent().getMediciones()));
                fecha.setText(model.getCurrent().getFecha());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        this.panel.revalidate();
    }

    public void clearTextFields(){
        model.mode = 1;
        numero.setText(null);
        mediciones.setText(null);
        fecha.setText(null);
        delete.setEnabled(false);
    }
    public boolean isValid(){
        if(numero.getText().isEmpty() || mediciones.getText().isEmpty() || fecha.getText().isEmpty() ){
            return false;
        }
        return true;
    }

}

