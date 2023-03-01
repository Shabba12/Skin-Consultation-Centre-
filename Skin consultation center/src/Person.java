import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {

        private String name;
        private String surname;
        private Date dateOfBirth;
        private String mobileNumber;

        // Constructor
        public Person(String name, String surname, Date dateOfBirth, String mobileNumber) {
            this.name = name;
            this.surname = surname;
            this.dateOfBirth = dateOfBirth;
            this.mobileNumber = mobileNumber;
        }

        // Getters and setters for the instance variables
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getDateOfBirth() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(dateOfBirth);
            return formattedDate;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

    @Override
    public String toString() {
        return  "       Name = " + name + '\n' +
                "       Surname = " + surname + '\n' +
                "       DateOfBirth = " + dateOfBirth.toString() + '\n' +
                "       MobileNumber = " + mobileNumber +'\n'
                ;
    }
}
