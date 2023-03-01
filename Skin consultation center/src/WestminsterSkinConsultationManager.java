import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    private  ArrayList <Doctor>doctors;
    private  ArrayList <Patient>patients;
    private  ArrayList <Consultation>consultations;

    // Constructor
    public  WestminsterSkinConsultationManager() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.consultations = new ArrayList<>();
    }

    // Getters and setters for the instance variables
    public  ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(ArrayList<Consultation> consultations) {
        this.consultations = consultations;
    }

    // Methods to add a doctor, patient and consultation

    public  void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }


    public  void printDocList() {
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        int i =1;
        for (Object d:
             doctors) {
            System.out.println((i++)+". " + d.toString());
        }
    }



    public void printConsultation() {
        int i=1;
        for (Consultation c:
             consultations) {
            System.out.println((i++)+". "+ c.toString());
        }
    }


    public void printConsultationListGui(WestminsterSkinConsultationManager manager) {
        JFrame frame = new JFrame();

        JTable table = new JTable(new ConsultationTable(consultations));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(5).setPreferredWidth(20);
        table.setRowHeight(20);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    int selectedRow = table.getSelectedRow();
                    Consultation selectedObject = manager.consultations.get(selectedRow);
//                    JOptionPane.showMessageDialog(null, selectedObject);
                    ConsultationView consView = new ConsultationView(selectedObject,manager);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.CYAN);

        scrollPane.setPreferredSize(new Dimension(900, 400));

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(1000,600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel,BorderLayout.CENTER);


        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(100,100,100));


        JButton addConsultation = new JButton("Menu");
        addConsultation.addActionListener(e -> {
            try {
                StartingFrame startFrame = new StartingFrame(manager);
                frame.setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        addConsultation.setBounds(10,20,50,20);
        btnPanel.add(addConsultation);
        frame.add(btnPanel,BorderLayout.NORTH);
        frame.setBackground(new Color(50, 162, 135));

        frame.setVisible(true);

    }


    public void printDocListUi() {
        JFrame frame = new JFrame();

        JTable table = new JTable(new DoctorTable(doctors));

        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();

        scrollPane.setPreferredSize(new Dimension(800, 400));

        frame.setSize(1000,600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(new Color(50, 162, 135));
        frame.setBackground(new Color(50, 162, 135));
        frame.setVisible(true);

    }



    public void storeDocList(int getRes) {
        if (getRes == 1) {
            try {
                // Read the ArrayList from the file, if it exists
                FileInputStream fileIn = new FileInputStream("people.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                doctors = (ArrayList<Doctor>) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("ArrayList of Person objects read from people.ser");
            } catch (Exception e) {
                // If the file doesn't exist, create a new ArrayList
                System.out.println("file not found");
            }
//            int i = 1;
//            for (Object d :
//                    doctors) {
//                System.out.println((i++) + ". " + d.toString());
//            }
        } else if (getRes==2) {

            if (doctors.isEmpty()) {

                System.out.println("No doctors registered");
            }else {
                try {
                    // Write the ArrayList to a file
                    FileOutputStream fileOut = new FileOutputStream("people.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(doctors);
                    out.close();
                    fileOut.close();
                    System.out.println("ArrayList of Doctors saved to people.ser");
                } catch (Exception e) {
                    System.out.println("Error");
                }
                // Print the ArrayList
                int i = 1;
                for (Object d :
                        doctors) {
                    System.out.println((i++) + ". " + d.toString());
                }
            }

        }else {
            System.out.println("Wrong input");
        }
    }




    public void addPatient(Patient patient) {
        patients.add(patient);
    }


    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

    // Methods to remove a doctor, patient and consultation

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
        System.out.println("Doctor "+doctor.getName()+" has been deleted!");
    }


    public void removePatient(Patient patient) {
        patients.remove(patient);
    }


    public void removeConsultation(Consultation consultation) {
        consultations.remove(consultation);
    }


}
// while(foundDoc){
//         int i=0;
//         int num = random.nextInt(manager.getDoctors().size());
//         Doctor exDoc = manager.getDoctors().get(num);
//         for (Consultation con:
//         manager.getConsultations()) {
//         if (exDoc.equals(con.getDoctor()) && (myStartDateObj.after(con.getEndDateAndTime()) || myEndDateObj.before(con.getStartDateAndTime())))
//         {
//         i++;
//         }else {
//         if (myStartDateObj.after(con.getEndDateAndTime()) || myEndDateObj.before(con.getStartDateAndTime())){
//         i++;
//         }
//         }
//         }
//         if (i>0){
//         docNum = num;
//         System.out.println("Doctor "+ exDoc.getName()+ " has been appointed for your consultation");
//         foundDoc = false;
//         }
//         }

//    int freeDoc = 0;
//    boolean getVal = false;
//                for (Consultation c:
//                        manager.getConsultations()) {
//                        if (manager.getDoctors().get(docNum).equals(c.getDoctor())){
//                        if (((myStartDateObj.after(c.getStartDateAndTime()) && myStartDateObj.before(c.getEndDateAndTime())) || myStartDateObj.equals(c.getStartDateAndTime()))|| ((myEndDateObj.after(c.getStartDateAndTime()) && myEndDateObj.before(c.getEndDateAndTime()) )|| myEndDateObj.equals(c.getEndDateAndTime()))) {
//                        System.out.println("Sorry the doctor is not available at this time slot");
//                        System.out.println("the next available doctor will be appointed ");
//
//                        boolean foundDoc= true;
//                        while(foundDoc){
//                        int i=0;
//                        int num = random.nextInt(manager.getDoctors().size());
//                        Doctor exDoc = manager.getDoctors().get(num);
//                        for (Consultation con:
//                        manager.getConsultations()) {
//                        if (exDoc.equals(con.getDoctor()) && ((myStartDateObj.after(con.getEndDateAndTime())) || myStartDateObj.equals(con.getEndDateAndTime())) || ((myEndDateObj.before(con.getStartDateAndTime())) || myEndDateObj.equals(con.getStartDateAndTime())))
//                        {
//                        i++;
//                        }else {
//                        if ((myStartDateObj.after(con.getEndDateAndTime()) || myStartDateObj.equals(con.getEndDateAndTime())) || (myEndDateObj.before(con.getStartDateAndTime()) || myEndDateObj.equals(con.getStartDateAndTime()))){
//                        i++;
//                        }
//                        }
//                        }
//                        if (i>0){
//                        freeDoc = num;
//                        System.out.println("Doctor "+ exDoc.getName()+ " has been appointed for your consultation");
//                        foundDoc = false;
//                        }
//                        }
//                        getVal = true;
//                        break;
//                        }
//                        }else {
//                        getVal = false;
//                        break;
//                        }
//                        }
//myStartDateObj.after(c.getStartDateAndTime()) && myStartDateObj.before(c.getEndDateAndTime()) || myEndDateObj.after(c.getStartDateAndTime()) && myEndDateObj.before(c.getEndDateAndTime())