package classroom;

public class Student {

    public String firstName;
    public String lastName;
    public String bestSubject;

    public Student(String firstName, String lasName, String bestSubject) {
        this.firstName = firstName;
        this.lastName = lasName;
        this.bestSubject = bestSubject;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBestSubject(String bestSubject) {
        this.bestSubject = bestSubject;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBestSubject() {
        return bestSubject;
    }

    @Override
    public String toString() {
        return String.format(
                "Student: First Name= %s, Last Name= %s, Best Subject= %s", this.firstName, this.lastName, this.bestSubject);
    }
}
