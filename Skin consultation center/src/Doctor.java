import java.io.Serializable;
import java.util.Date;

public class Doctor extends Person implements Serializable {
    private String medicalLicenceNumber;
    private String specialisation;

    @Override
    public String toString() {
        return  "DOCTOR DETAILS : " + '\n' +
                super.toString() +
                "       Medical Licence Number = " + medicalLicenceNumber + '\n' +
                "       Specialization = " + specialisation  + '\n' ;
    }

    // Constructor
    public Doctor(String name, String surname, Date dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    // Getters and setters for the instance variables
    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
