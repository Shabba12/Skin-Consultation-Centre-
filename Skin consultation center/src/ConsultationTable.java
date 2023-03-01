import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ConsultationTable extends AbstractTableModel {

    private final String[] colunmNames = {"Name","Surname","Date of Birth","Mobile number","Doctor","Doctor ID","Start Time","End Time"};
    private ArrayList<Consultation> consultationList;

    public ConsultationTable (ArrayList<Consultation> consultationList){
        this.consultationList = consultationList;
    }
    @Override
    public int getRowCount() {
        return consultationList.size();
    }

    @Override
    public int getColumnCount() {
        return colunmNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object temp = null;
        if (columnIndex == 0) {
            temp = consultationList.get(rowIndex).getPatient().getName();
        }
        else if (columnIndex == 1) {
            temp = consultationList.get(rowIndex).getPatient().getSurname();
        }
        else if (columnIndex == 2) {
            temp = consultationList.get(rowIndex).getPatient().getDateOfBirth();
        }
        else if (columnIndex ==3) {
            temp = consultationList.get(rowIndex).getPatient().getMobileNumber();

        } else if (columnIndex == 4) {
            temp = consultationList.get(rowIndex).getDoctor().getName();

        } else if (columnIndex == 5) {
            temp = consultationList.get(rowIndex).getDoctor().getMedicalLicenceNumber();

        } else if (columnIndex == 6) {
            temp = consultationList.get(rowIndex).getSimpleStartDateAndTime();

        }else if (columnIndex == 7) {
            temp = consultationList.get(rowIndex).getSimpleEndDateAndTime();

        }
        return temp;
    }

    public String getColumnName(int col) {
        return colunmNames[col];
    }
}

