package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;

public class ModelPersona extends AbstractTableModel {

    public String[] columnas = {"Cedula", "Nombre", "Direccion", "Fecha de Nacimiento"};
    public Class[] columnasTipos = {String.class, String.class, String.class, String.class};
    private List<Persona> datos;

    public ModelPersona() {
        super();
        datos = new ArrayList<Persona>();
    }

    public ModelPersona(List<Persona> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    public void setValueAt(Object value, int row, int col) {
        Persona dato = (Persona) (datos.get(row));

        switch (col) {
            case 0:
                dato.setDireccion((String) value);
                break;
            case 1:
                dato.setNombre((String) value);
                break;
            case 2:
                dato.setDireccion((String) value);
                break;
            case 3:
                dato.setFechaNac((String) value);
                break;
            default:
                break;
        }
    }

    public String getColumnName(int col) {
        return columnas[col];
    }

    public Class getColumnClass(int col) {
        return columnasTipos[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Persona dato = (Persona) (datos.get(row));

        switch (col) {
            case 0:
                return dato.getCedula();
            case 1:
                return dato.getNombre();
            case 2:
                return dato.getDireccion();
            case 3:
                return dato.getFechaNac();
            default:
                break;
        }
        return new String();
    }
}
