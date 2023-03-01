
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class WestminsterSkinConsultationTest {
    //initialize add doctor
    private final PrintStream standardOut = System.out;
    //capture console prints using new output stream
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static WestminsterSkinConsultationManager manager;

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
        //reset the doctorList Array to remove all elements
        manager = new WestminsterSkinConsultationManager();
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

    @Test
    void addDoctor(){
        manager.addDoctor(new Doctor("shabeer", "nuhman", new Date(2001 - 1900, 06 - 1, 27), "0774794741", "333", "cardio"));
        assertEquals(1,manager.getDoctors().size());
    }
    @Test
    void printDoctorWithData(){
        manager.addDoctor(new Doctor("shabeer", "nuhman", new Date(2001 - 1900, 06 - 1, 27), "0774794741", "333", "cardio"));
        manager.printDocList();
        String newExpectOp = """
                1. DOCTOR DETAILS :\s
                       Name = shabeer
                       Surname = nuhman
                       DateOfBirth = Wed Jun 27 00:00:00 IST 2001
                       MobileNumber = 0774794741
                       Medical Licence Number = 333
                       Specialization = cardio""";
        assertEquals(newExpectOp,outputStreamCaptor.toString().trim());
    }
    @Test
    void deleteDoctor(){
        Doctor d = new Doctor("shabeer", "nuhman", new Date(2001 - 1900, 06 - 1, 27), "0774794741", "333", "cardio");
        manager.addDoctor(d);
        manager.removeDoctor(d);
        String expectStr = "Doctor "+d.getName()+" has been deleted!";
        assertEquals(expectStr,outputStreamCaptor.toString().trim());
    }
    @Test
    void saveLoadInfo(){
        Doctor d = new Doctor("shabeer", "nuhman", new Date(2001 - 1900, 06 - 1, 27), "0774794741", "333", "cardio");
        manager.addDoctor(d);
        manager.storeDocList(2);
        manager.setDoctors(new ArrayList<>());
        manager.storeDocList(1);
        assertEquals("shabeer",manager.getDoctors().get(0).getName());
    }
}
