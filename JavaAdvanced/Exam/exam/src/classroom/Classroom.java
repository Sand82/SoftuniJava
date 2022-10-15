package classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Classroom {
    public List<Student> students;
    public int capacity;
    public Classroom(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStudentCount() {
        return students.size();
    }

    public String registerStudent(Student student) {

        if (students.size() < capacity) {

            Student currStudent = getStudent(student.firstName, student.lastName);

            if (currStudent == null) {
                students.add(student);
                return String.format("Added student %s %s", student.firstName, student.lastName);
            }

            return "Student is already in the classroom";
        }

        return "No seats in the classroom";
    }

    public String dismissStudent(Student student) {

        Student currStudent = getStudent(student.firstName, student.lastName);

        if (currStudent == null) {
            return "Student not found";
        }

        students.remove(currStudent);
        return String.format("Removed student %s %s", student.firstName, student.lastName);
    }

    public String getSubjectInfo(String subject) {

        List<Student> subjectList = students.stream().filter(s -> s.bestSubject.equals(subject)).collect(Collectors.toList());

        if (subjectList.size() == 0) {
            return "No students enrolled for the subject";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Subject: %s", subject));
        sb.append(System.lineSeparator());
        sb.append("Students:");
        sb.append(System.lineSeparator());

        for (var student : subjectList) {
            sb.append(String.format("%s %s", student.firstName, student.lastName));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public Student getStudent(String firstName, String lastName) {
        return students.stream()
                .filter(s -> s.firstName.equals(firstName) && s.lastName.equals(lastName))
                .findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Classroom size: %d%n", getStudentCount()));

        int count = 1;

        for (var student : students) {

            if (count < students.size() ) {
                sb.append(String.format("==Student: First Name= %s, Last Name= %s, Best Subject= %s%n",
                        student.firstName, student.lastName, student.bestSubject));
            }else {
                sb.append(String.format("==Student: First Name= %s , Last Name= %s , Best Subject= %s",
                        student.firstName, student.lastName, student.bestSubject));
            }

            count++;
        }

        return sb.toString().trim();
    }
}
