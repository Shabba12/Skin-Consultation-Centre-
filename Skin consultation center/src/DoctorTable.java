import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class DoctorTable extends AbstractTableModel {

    private final String[] colunmNames = {"Name","Surname","Date of Birth","Mobile number","MedicalLicenceNumber","Specialization"};
    private ArrayList<Doctor> doctors;

    public DoctorTable (ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }
    @Override
    public int getRowCount() {
        return doctors.size();
    }

    @Override
    public int getColumnCount() {
        return colunmNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object temp = null;
        if (columnIndex == 0) {
            temp = doctors.get(rowIndex).getName();
        }
        else if (columnIndex == 1) {
            temp = doctors.get(rowIndex).getSurname();
        }
        else if (columnIndex == 2) {
            temp = doctors.get(rowIndex).getDateOfBirth().toString();
        }
        else if (columnIndex ==3) {
            temp = doctors.get(rowIndex).getMobileNumber();

        } else if (columnIndex == 4) {
            temp = doctors.get(rowIndex).getMedicalLicenceNumber();

        } else if (columnIndex == 5) {
            temp = doctors.get(rowIndex).getSpecialisation();

        }
        return temp;
    }

    public String getColumnName(int col) {
        return colunmNames[col];
    }

}
