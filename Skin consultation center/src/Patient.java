import java.util.Date;

public class Patient extends Person{
    private String patientId;

    // Constructor
    public Patient(String name, String surname, Date dateOfBirth, String mobileNumber, String patientId) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.patientId = patientId;
    }

    // Getter and setter for the patientId instance variable
    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Patient ID: " +'\n' +
                "       PatientId = " + patientId +'\n' +
                "Patient Details : " + '\n' +
                super.toString()
                ;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
