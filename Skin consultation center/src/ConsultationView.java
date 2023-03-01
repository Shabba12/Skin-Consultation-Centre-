import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConsultationView extends JFrame{


    private WestminsterSkinConsultationManager manager;

    public ConsultationView(Consultation cons,WestminsterSkinConsultationManager manager) {
        setLayout(new FlowLayout());
        JPanel patientPanel = new JPanel(new GridLayout(4,2,0,0));
        JPanel doctorPanel = new JPanel(new GridLayout(5,2,0,0));
        JPanel consultPanel = new JPanel(new GridLayout(3,2,0,0));

        Border blackline = BorderFactory.createLineBorder(Color.black);

        patientPanel.setBounds(10, 100, 300, 300);
        patientPanel.setBackground(new Color(50, 162, 135, 208));
        patientPanel.setBorder(blackline);
        doctorPanel.setBounds(360, 100, 320, 300);
        doctorPanel.setBackground(new Color(50, 162, 135,208));
        doctorPanel.setBorder(blackline);
        consultPanel.setBounds(710,100,300,200);
        consultPanel.setBackground(new Color(50, 162, 135,208));
        consultPanel.setBorder(blackline);

        JLabel patientFullName = new JLabel("Patient Name:");
        patientFullName.setForeground(Color.BLACK);
        patientPanel.add(patientFullName);

        JLabel pFullNameLabel = new JLabel(cons.getPatient().getName()+" "+cons.getPatient().getSurname());
        pFullNameLabel.setForeground(Color.BLACK);
        pFullNameLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        patientPanel.add(pFullNameLabel);

        JLabel mobileNumber = new JLabel("Mobile Number:");
        mobileNumber.setForeground(Color.BLACK);
        patientPanel.add(mobileNumber);

        JLabel mobileNumberLabel = new JLabel(cons.getPatient().getMobileNumber());
        mobileNumberLabel.setForeground(Color.BLACK);
        mobileNumberLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        patientPanel.add(mobileNumberLabel);

        JLabel dateOfBirth = new JLabel("Date Of Birth:");
        dateOfBirth.setForeground(Color.BLACK);
        patientPanel.add(dateOfBirth);

        JLabel dateOfBirthLabel = new JLabel(cons.getPatient().getDateOfBirth());
        dateOfBirthLabel.setForeground(Color.BLACK);
        dateOfBirthLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        patientPanel.add(dateOfBirthLabel);

        JLabel pID = new JLabel("Patient ID:");
        pID.setForeground(Color.BLACK);
        patientPanel.add(pID);

        JLabel pIDLabel = new JLabel(cons.getPatient().getPatientId());
        pIDLabel.setForeground(Color.BLACK);
        pIDLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        patientPanel.add(pIDLabel);

        JLabel dName = new JLabel("Doctor Name:");
        dName.setForeground(Color.BLACK);
        doctorPanel.add(dName);

        JLabel dNameLabel = new JLabel(cons.getDoctor().getName()+" "+cons.getDoctor().getSurname());
        dNameLabel.setForeground(Color.BLACK);
        dNameLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        doctorPanel.add(dNameLabel);

        JLabel dMobileNum = new JLabel("Mobile Number:");
        dMobileNum.setForeground(Color.BLACK);
        doctorPanel.add(dMobileNum);

        JLabel dMobileNumLabel = new JLabel(cons.getDoctor().getMobileNumber());
        dMobileNumLabel.setForeground(Color.BLACK);
        dMobileNumLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        doctorPanel.add(dMobileNumLabel);

        JLabel dDateOfBirth = new JLabel("Date of birth:");
        dDateOfBirth.setForeground(Color.BLACK);
        doctorPanel.add(dDateOfBirth);

        JLabel dDateOfBirthLabel = new JLabel(cons.getDoctor().getDateOfBirth());
        dDateOfBirthLabel.setForeground(Color.BLACK);
        dDateOfBirthLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        doctorPanel.add(dDateOfBirthLabel);

        JLabel dMedID = new JLabel("Medical ID:");
        dMedID.setForeground(Color.BLACK);
        doctorPanel.add(dMedID);

        JLabel dMedIDLabel = new JLabel(cons.getDoctor().getMedicalLicenceNumber());
        dMedIDLabel.setForeground(Color.BLACK);
        dMedIDLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        doctorPanel.add(dMedIDLabel);

        JLabel dSpec = new JLabel("Specialization:");
        dSpec.setForeground(Color.BLACK);
        doctorPanel.add(dSpec);

        JLabel dSpecLabel = new JLabel(cons.getDoctor().getSpecialisation());
        dSpecLabel.setForeground(Color.BLACK);
        dSpecLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        doctorPanel.add(dSpecLabel);

        JLabel startDate = new JLabel("Start Time:");
        startDate.setForeground(Color.BLACK);
        consultPanel.add(startDate);

        JLabel startDateLabel = new JLabel(cons.getSimpleStartDateAndTime());
        startDateLabel.setForeground(Color.BLACK);
        startDateLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        consultPanel.add(startDateLabel);

        JLabel endDate = new JLabel("End Time:");
        endDate.setForeground(Color.BLACK);
        consultPanel.add(endDate);

        JLabel endDateLabel = new JLabel(cons.getSimpleEndDateAndTime());
        endDateLabel.setForeground(Color.BLACK);
        endDateLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        consultPanel.add(endDateLabel);

        JLabel cost = new JLabel("Cost:");
        cost.setForeground(Color.BLACK);
        consultPanel.add(cost);

        JLabel costLabel = new JLabel(cons.getCost()+" GBP");
        costLabel.setForeground(Color.BLACK);
        costLabel.setFont(new Font("Serif", Font.ITALIC, 15));
        consultPanel.add(costLabel);

        JLabel notes = new JLabel("Note:");
        notes.setBounds(710,300,40,20);
        notes.setForeground(Color.BLACK);
        add(notes);


        if (cons.isImageNote()){
         JLabel notesLabel = new JLabel();
         notesLabel.setBounds(760,300,250,250);
         Image exImage = cons.getGetImageIcon().getImage();
         exImage = exImage.getScaledInstance(notesLabel.getWidth(), notesLabel.getHeight(), Image.SCALE_SMOOTH);
         ImageIcon finalImage = new ImageIcon(exImage);
         notesLabel.setIcon(finalImage);
         add(notesLabel);
        }else{
            JLabel notesLabel = new JLabel(cons.getNotes());
            notesLabel.setForeground(Color.BLACK);
            notesLabel.setOpaque(true);
            notesLabel.setFont(new Font("Serif", Font.ITALIC, 12));
            notesLabel.setBackground(Color.white);
            notesLabel.setBounds(760,300,250,200);
            add(notesLabel);
        }

        JButton startBtn = new JButton("Back");
        startBtn.setBounds(10,450,70,20);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.printConsultation();
                setVisible(false);
            }
        });

        add(patientPanel);
        add(doctorPanel);
        add(consultPanel);
        add(startBtn);
        setForeground(Color.white);

        setTitle("Consultation");
        getContentPane().setBackground(new Color(50, 162, 135));
        setSize(1100,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
