package instrumentos.presentation.Instrumentos;

import instrumentos.logic.Instrumentos;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Instrumentos> rows;
    int[] cols;

    public TableModel(int[] cols, List<Instrumentos> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int row, int col) {
        Instrumentos sucursal = rows.get(row);
        switch (cols[col]){
            case SERIE: return sucursal.getSerie();
            case DESCRIPCION: return sucursal.getDescripcion();
            case MINIMO: return sucursal.getMinimo();
            case MAXIMO: return sucursal.getMaximo();
            case TOlERANCIA: return sucursal.getTolerancia();
            default: return "";
        }
    }

    public Instrumentos getRowAt(int row) {
        return rows.get(row);
    }

    public static final int SERIE=0;
    public static final int DESCRIPCION=1;
    public static final int MINIMO=2;
    public static final int MAXIMO=3;
    public static final int TOlERANCIA=4;

    String[] colNames = new String[6];
    private void initColNames(){
        colNames[SERIE]= "No. Serie";
        colNames[DESCRIPCION]= "Descripcion";
        colNames[MINIMO]= "Minimo";
        colNames[MAXIMO]= "Maximo";
        colNames[TOlERANCIA]= "Tolerancia";

    }

}
