import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class ConsultationGUI extends JFrame{
    static Random random = new Random();
    private WestminsterSkinConsultationManager manager;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final SimpleDateFormat dateFormatDOB = new SimpleDateFormat("dd/MM/yyyy");


    public ConsultationGUI(WestminsterSkinConsultationManager manager) {

        this.manager = manager;

        JPanel p = new JPanel(new GridLayout(8, 2, 0, 20));
        p.setBounds(100, 200, 500, 300);
        p.setBackground(new Color(50, 162, 135));

        JLabel patientName = new JLabel("First name");
        setForeground(Color.BLACK);
        p.add(patientName);

        JTextField patientNameText = new JTextField(20);
        p.add(patientNameText);

        JLabel patientSurName = new JLabel("Surname:");
        setForeground(Color.BLACK);
        p.add(patientSurName);

        JTextField patientSurNameText = new JTextField(20);
        p.add(patientSurNameText);

        JLabel DateOfBirth = new JLabel("Date Of Birth (dd/MM/yyyy):");
        setForeground(Color.BLACK);
        p.add(DateOfBirth);

        JTextField dateOfBirthText = new JTextField(20);
        p.add(dateOfBirthText);

        JLabel mobileNum = new JLabel("Mobile Number:");
        setForeground(Color.BLACK);
        p.add(mobileNum);

        JTextField mobileNumText = new JTextField(10);
        p.add(mobileNumText);

        JLabel patientId = new JLabel("Patient ID:");
        setForeground(Color.BLACK);
        p.add(patientId);

        JTextField patientIdText = new JTextField(10);
        p.add(patientIdText);


        JLabel startDateTime = new JLabel("Start Time (dd/MM/yyyy HH:mm):");
        setForeground(Color.BLACK);
        p.add(startDateTime);

        JTextField startDateTimeText = new JTextField(20);
        p.add(startDateTimeText);

        JLabel endDateTime = new JLabel("End Time (dd/MM/yyyy HH:mm):");
        setForeground(Color.BLACK);
        p.add(endDateTime);

        JTextField EndDateTimeText = new JTextField(20);
        p.add(EndDateTimeText);

        JLabel doctorList = new JLabel("Select a doctor:");
        setForeground(Color.BLACK);
        p.add(doctorList);

        String[] choices = new String[manager.getDoctors().size()];
        for (int i = 0; i < manager.getDoctors().size(); i++) {
            choices[i] = manager.getDoctors().get(i).getName();
        }
        JComboBox<String> docId = new JComboBox<String>(choices);
        p.add(docId);

        JLabel notes = new JLabel("Notes:");
        setForeground(Color.BLACK);
        notes.setBounds(700, 200, 50, 50);
        add(notes);

        JTextArea notesArea = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        notesArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.setBounds(800, 200, 270, 300);
        add(notesArea);

        var ref = new Object() {
            String Notes = "";
            boolean textNote = false;

            byte[] encryptedImageBytes;
        };

        JButton addNoteButton = new JButton("Add Note");
        addNoteButton.setBounds(820, 570, 100, 25);
        add(addNoteButton);

        JLabel displayStrImg = new JLabel("Empty");
        displayStrImg.setBounds(800, 200, 300, 300);
        add(displayStrImg);
        displayStrImg.setVisible(false);


        JButton uploadImageButton = new JButton("Upload");
        uploadImageButton.setBounds(950, 570, 100, 25);
        uploadImageButton.addActionListener(e -> {

            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Image image = ImageIO.read(selectedFile);
                    image = image.getScaledInstance(displayStrImg.getWidth(), displayStrImg.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(image);
                    //this is to display the image or string
                    ref.Notes = selectedFile.getName();
                    displayStrImg.setIcon(imageIcon);
                    displayStrImg.setVisible(true);
                    notesArea.setVisible(false);
                    addNoteButton.setVisible(false);
                    //encrypting the image
                    BufferedImage buffImage = ImageIO.read(new File(String.valueOf(selectedFile)));

                    // Convert the image to a byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(buffImage, "jpg", baos);
                    baos.flush();
                    byte[] imageBytes = baos.toByteArray();
                    baos.close();

                    // Encrypt the image
                    ref.encryptedImageBytes = encrypt(imageBytes, "mysecretkey");


                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    System.out.println(ex);;
                }
            }
        });
        add(uploadImageButton);

        //gets the text entered in the text field
        addNoteButton.addActionListener(e -> {
            ref.Notes = notesArea.getText();
            ref.textNote = true;
            uploadImageButton.setVisible(false);

        });

        JButton submit = new JButton("Submit");
        submit.setBounds(1080, 570, 100, 25);
        submit.addActionListener(e -> {
            //gets all the text entered in there dedicated jtextfield into variables
            String patName = patientNameText.getText().toLowerCase().trim();
            String patSurName = patientSurNameText.getText().toLowerCase().trim();
            String startDateTimeVar = startDateTimeText.getText().toLowerCase().trim();
            String mNum = mobileNumText.getText().toLowerCase().trim();
            String pId = patientIdText.getText().toLowerCase().trim();
            String endDateTimeVar = EndDateTimeText.getText().toLowerCase().trim();
            String dateOfBirthVar = dateOfBirthText.getText().toLowerCase().trim();
            String getNotes = ref.Notes;

            //checks if all jtextfields are filled
            if (patName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "First name required");
                patientNameText.requestFocus();
            } else if (patSurName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Surname required");
                patientSurNameText.requestFocus();
            } else if (mNum.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mobile number required");
                mobileNumText.requestFocus();
            } else if (pId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Patient ID required");
                patientIdText.requestFocus();
            } else if (getNotes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Notes or image required");
                notesArea.requestFocus();
            } else if (startDateTimeVar.isEmpty()) {
                JOptionPane.showMessageDialog(null, " Start Date Time required");
                startDateTimeText.requestFocus();
            } else if (endDateTimeVar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "End Date Time required");
                EndDateTimeText.requestFocus();
            } else if (dateOfBirthVar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Date Of Birth required");
                dateOfBirthText.requestFocus();
            } else {

                //saves the start time into a Date object
                Date startDateTimeObj;
                try {
                    startDateTimeObj = DATE_FORMAT.parse(startDateTimeVar);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Start Date Time");
                    startDateTimeText.setText("");
                    startDateTimeText.requestFocus();
                    throw new RuntimeException(ex);
                }
                //saves the end time into a Date object
                Date endDateTimeObj;
                try {
                    endDateTimeObj = DATE_FORMAT.parse(endDateTimeVar);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid End Date Time");
                    EndDateTimeText.setText("");
                    EndDateTimeText.requestFocus();
                    throw new RuntimeException(ex);
                }
                //saves the date of birth into a Date object
                Date dateOfBirthObj;
                try {
                    dateOfBirthObj = dateFormatDOB.parse(dateOfBirthVar);
                } catch (ParseException ex) {

                    JOptionPane.showMessageDialog(null, "Invalid Date Of Birth");
                    dateOfBirthText.setText("");
                    dateOfBirthText.requestFocus();
                    throw new RuntimeException(ex);
                }

                //this method checks gets the cost for the consultation where the system will check if the patient is new and give the cost
                long timeDifference = Math.abs(startDateTimeObj.getTime() - endDateTimeObj.getTime());
                double timeDifferenceInHours = (double) timeDifference / (1000 * 60 * 60);
                System.out.println(timeDifferenceInHours);

                boolean checkCost = false;
                for (Patient patients :
                        manager.getPatients()) {
                    if (pId.equals(patients.getPatientId())) {
                        checkCost = true;
                        double costForcast = timeDifferenceInHours * 25;
                    } else {
                        checkCost = false;
                        double costForcast = timeDifferenceInHours * 15;
                    }
                }
                double cost;
                if (checkCost) {
                    cost = timeDifferenceInHours * 25;
                } else {
                    cost = timeDifferenceInHours * 15;
                }

                //method to capture the doctor object from the jcombobox field
                Doctor doctor = null;
                for (Doctor d :
                        manager.getDoctors()) {
                    if (d.getName().equals(docId.getSelectedItem().toString())) {
                        doctor = d;
                    }
                }

                //this method gets nect available doctors and saving it into another arraylist of objects
                boolean checkAvailableDoc = false;

                for (Consultation c:
                     manager.getConsultations()) {
                    if (doctor.equals(c.getDoctor())){
                        if (((startDateTimeObj.after(c.getStartDateAndTime()) && startDateTimeObj.before(c.getEndDateAndTime())) || startDateTimeObj.equals(c.getStartDateAndTime())) || ((endDateTimeObj.after(c.getStartDateAndTime())&& endDateTimeObj.before(c.getEndDateAndTime()) || endDateTimeObj.equals(c.getEndDateAndTime()) ))) {
                            checkAvailableDoc = true;
                        }
                    }
                }
                ArrayList<Doctor> getAavailableDoc = new ArrayList<>();
                if (checkAvailableDoc){
                    for (Doctor doc:
                         manager.getDoctors()) {
                        for (Consultation cons:
                             manager.getConsultations()) {
                            if (doc.equals(cons.getDoctor())) {
                                if ((cons.getStartDateAndTime().equals(endDateTimeObj) || cons.getEndDateAndTime().equals(startDateTimeObj)) || ((cons.getStartDateAndTime().before(startDateTimeObj) || cons.getStartDateAndTime().after(endDateTimeObj)) && (cons.getEndDateAndTime().before(startDateTimeObj) || cons.getEndDateAndTime().after(endDateTimeObj)))) {
                                    getAavailableDoc.add(doc);
                                }
                            }
                        }if (!manager.getConsultations().contains(doc)) {
                            getAavailableDoc.add(doc);
                        }
                    }
                    //a randome number is selected within the availble list of doctors
                    int num = random.nextInt(getAavailableDoc.size());
                    doctor = getAavailableDoc.get(num);
                    JOptionPane.showMessageDialog(null, "Doctor not available \nDoctor : "+doctor.getName()+" has been randomly appointed!");
                }




                Patient pat = new Patient(patName, patSurName, dateOfBirthObj, mNum, pId);
                manager.addPatient(pat);

                try {
                    String encryptNote = encrypt(getNotes);
                    String decryptNote = decrypt(encryptNote);
                    if (ref.textNote) {
                        Consultation consult = new Consultation(startDateTimeObj, endDateTimeObj, cost, decryptNote, doctor, pat);
                        System.out.println("Consultation has been recorded");
                        manager.addConsultation(consult);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        StartingFrame startFrame = new StartingFrame(manager);
                        setVisible(false);
                    } else {
                        //decrypt image

                        byte[] decryptedImageBytes = decrypt(ref.encryptedImageBytes, "mysecretkey");

                        // Convert the decrypted image bytes back to a BufferedImage
                        BufferedImage decryptedImage = ImageIO.read(new ByteArrayInputStream(decryptedImageBytes));
                        ImageIcon decryptImageIcon = new ImageIcon(decryptedImage);
                        Consultation consult = new Consultation(startDateTimeObj, endDateTimeObj, cost, decryptImageIcon, doctor, pat);
                        System.out.println("Consultation has been recorded");
                        manager.addConsultation(consult);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        StartingFrame startFrame = new StartingFrame(manager);
                        setVisible(false);
                    }
//                    Consultation consult = new Consultation(startDateTimeObj,endDateTimeObj,cost,decryptNote,doctor,pat);


                } catch (Exception ex) {
                    System.out.println(ex);
                    ;
                }
                JOptionPane.showMessageDialog(null, "Consultation has been submitted");
            }
        });
        add(submit);


        add(p);
        setForeground(Color.white);

        //setting up the jFrame
        setTitle("Consultation Center");
        getContentPane().setBackground(new Color(50, 162, 135));
        setSize(1200, 1400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //method to encrypt the text note
    public static String encrypt(String input) throws Exception {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    //method to decrypt the text note
    public static String decrypt(String input) throws Exception {
        return new String(Base64.getDecoder().decode(input));
    }

    //method to encrypt the image
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    //method to decrypt the image
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }




}
