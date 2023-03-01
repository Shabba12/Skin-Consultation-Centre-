import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) throws IOException {
        // Create a new instance of the WestminsterSkinConsultationManager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        System.out.println("Welcome");

        char quit = 'n';
        char selectMethod;
        String get;

        while (quit != 'y') {
            System.out.println("================================================");
            System.out.println("Enter 'A' to Add a doctor to the consultation center");
            System.out.println("Enter 'D' to Delete Doctor from consultation center");
            System.out.println("Enter 'P' to Print the list of doctors");
            System.out.println("Enter 'S' to Store the list of doctors into file");
            System.out.println("Enter 'X' to generate GUI to view doctors list");
            System.out.println("Enter 'C' to generate Consultation GUI");
            System.out.println("================================================");

            System.out.println("Enter your choice:");
            selectMethod = input.next().charAt(0);
            selectMethod = Character.toUpperCase(selectMethod);

            // method breakdown using switch case

            switch (selectMethod) {
                case 'A':
                    addDoctor(manager);
                    break;
                case 'D':
                    deleteDoctor(manager);
                    break;
                case 'P':
                    printDocList(manager);
                    break;
                case 'S':
                    storeDocList(manager);
                    break;
                case 'X':
                    PrintDocListUI(manager);
                    break;
                case 'C':
                    GuiConsultation(manager);
                    break;

                default:
                    System.out.println("invalid choice");      //throws error message if user enters invalid input

            }
            System.out.println("Would you like to quit the program y/n: ");
            get = input.next().toLowerCase().trim();
            quit = get.charAt(0);
        }
    }


    //method to print the doctors list
    private static void PrintDocListUI(WestminsterSkinConsultationManager manager) {
        //using the object created at the start to run the program to print doctors list using a GUI
        if (manager.getDoctors().size() == 0){
            System.out.println("No doctors available!");
        }else {
            manager.printDocListUi();
        }
    }
    private static void printDocList(WestminsterSkinConsultationManager manager){
        if (manager.getDoctors().size() == 0){
            System.out.println("No doctors available!");
        }else {
            manager.printDocList();
        }
    }

    //method to store doctors list
    private static void storeDocList(WestminsterSkinConsultationManager manager) {
        //asking user to save or read the file
        System.out.println("Enter 1 to read the file \nEnter 2 to save the file: ");
        int getRes = input.nextInt();

        //using the object to run the method
        manager.storeDocList(getRes);
    }

    //method to delete a doctor from the list
    private static void deleteDoctor(WestminsterSkinConsultationManager manager) {
        //getting the doctors med ID
        try {
            input.nextLine();
            System.out.println("Enter Doctor medical licence number:");
            String getDocNum = input.nextLine().toLowerCase().trim();

            for (Doctor d:
                    manager.getDoctors()) {
                if (d.getMedicalLicenceNumber().equals(getDocNum)){
                    //removing the doctor from the list using the med ID
                    manager.removeDoctor(d);
                    System.out.println("Number of doctors: "+manager.getDoctors().size());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    //method to begin the GUI
    private static void GuiConsultation( WestminsterSkinConsultationManager manager) throws IOException {
        //generating the GUI by initializing the class using the constructor
        StartingFrame startFrame = new StartingFrame(manager);
    }


    //method to add a doctor
    private static void addDoctor(WestminsterSkinConsultationManager manager) {
        try {
            if (manager.getDoctors().size()<10) {
                boolean keepGoing = true;
                char quit = 'y';
                while (keepGoing) {
                    //getting doctors first name from the admin
                    input.nextLine();
                    System.out.println("Enter Doctor first name: ");
                    String Name = input.nextLine().toLowerCase().trim();

                    //Getting doctors surname from the admin

                    System.out.println("Enter Doctor surname: ");
                    String surName = input.nextLine().toLowerCase().trim();

                    //Getting doctors date of birth

                    System.out.println("Please enter the Date Of Birth in numbers! ");
                    System.out.println("Enter Day: ");
                    int day = Integer.parseInt(input.nextLine().toLowerCase().trim());
                    System.out.println("Enter Month: ");
                    int month = Integer.parseInt(input.nextLine().toLowerCase().trim());
                    System.out.println("Enter Year: ");
                    int year = Integer.parseInt(input.nextLine().toLowerCase().trim());

                    //getting doctors mobile number

                    System.out.println("Enter Mobile number: ");
                    String mobileNumber = input.nextLine().toLowerCase().trim();

                    //getting doctors medical number

                    System.out.println("Enter MEDICAL LICENSE NUMBER");
                    String medicalLicenceNumber = input.nextLine().toLowerCase().trim();

                    //getting doctors related medical field

                    System.out.println("Enter SPECIALIZATION FIELD");
                    String specialization = input.nextLine().toLowerCase().trim();


                    //creating a date of birth class using the Date class


                    //adding the collected details into a Doctor class as well as adding it to the array list of objects
                    Doctor doctor = new Doctor(Name, surName, new Date(year - 1900, month - 1, day), mobileNumber, medicalLicenceNumber, specialization);
                    //saving the doctor to the doctor list array
                    manager.addDoctor(doctor);
                    System.out.println("Doctor registered");
                    System.out.println("");
                    System.out.println("Doctor List: ");

                    //printing the collected doctor details from the arraylist
                    manager.printDocList();

                    //asking user whether to add  another doctor
                    System.out.println("Do you want to register another Doctor? y/n");
                    String get = input.nextLine().toLowerCase().trim();
                    quit = get.charAt(0);

                    if (quit == 'n') {
                        keepGoing = false;
                    } else {
                        keepGoing = true;
                    }
                }
            }else {
                System.out.println("Doctor capacity reached!");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }


}

