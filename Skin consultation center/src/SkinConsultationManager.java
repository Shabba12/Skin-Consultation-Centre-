import java.util.ArrayList;
import java.util.Date;

public interface SkinConsultationManager {


    // Methods to add a doctor, patient and consultation
     void addDoctor(Doctor doctor);

     void printDocList();

    void printConsultation();


    void printConsultationListGui(WestminsterSkinConsultationManager manager);

    void printDocListUi();

    void storeDocList(int getRes);

    void addPatient(Patient patient);
    void addConsultation(Consultation consultation);
    void removeDoctor(Doctor doctor);
    void removePatient(Patient patient);
    void removeConsultation(Consultation consultation);

}
