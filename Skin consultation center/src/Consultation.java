import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consultation {

    private Date startDateAndTime;
    private Date endDateAndTime;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;

    private ImageIcon getImageIcon;

    private boolean imageNote = false;


    // Constructor

    public Consultation(Date startDateAndTime, Date endDateAndTime, double cost, String notes, Doctor doctor, Patient patient) {

        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.cost = cost;
        this.notes = notes;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Consultation(Date startDateAndTime, Date endDateAndTime, double cost, ImageIcon getImageIcon, Doctor doctor, Patient patient) {

        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.cost = cost;
        this.getImageIcon = getImageIcon;
        this.doctor = doctor;
        this.patient = patient;
        this.imageNote = true;
    }

    // Getters and setters for the instance variables


    public Date getStartDateAndTime() {

        return startDateAndTime;
    }

    public boolean isImageNote() {
        return imageNote;
    }

    public String getSimpleStartDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(startDateAndTime);
    }
    public String getSimpleEndDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(endDateAndTime);
    }

    public ImageIcon getGetImageIcon() {
        return getImageIcon;
    }

    public void setStartDateAndTime(Date startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public Date getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime(Date endDateAndTime) {
        this.endDateAndTime = endDateAndTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return  "----------------------------------------------------------------"+ '\n'+
                "Consultation Details: " +'\n' +
                "       dateAndTime = " + startDateAndTime.toString() + '\n' +
                "       dateAndTime = " + endDateAndTime.toString() + '\n' +
                "       cost = " + cost + "GBP"+ '\n' +
                "       notes = " + notes + '\n' +
                patient.toString()+ '\n' +
                doctor.toString()+ '\n'+
                "----------------------------------------------------------------"
                ;
    }
}
