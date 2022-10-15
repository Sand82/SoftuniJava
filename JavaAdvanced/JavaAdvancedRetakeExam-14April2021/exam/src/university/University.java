package university;

import java.util.ArrayList;
import java.util.List;

public class University {

    public int capacity;
    public List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String registerStudent(Student student) {

        Student currStudent = getStudent(student.getFirstName(), student.getLastName());

        if (students.size() >= capacity) {
            return "No seats in the university";
        }

        if (currStudent == null) {
            students.add(student);
            return String.format("Added student %s %s", student.getFirstName(), student.getLastName());
        }

        return "Student is already in the university";
    }

    public Student getStudent(String firstName, String lastName) {
        return  students.stream()
                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }

    public String dismissStudent(Student student) {

        Student currStudent = getStudent(student.getFirstName(), student.getLastName());

        if (currStudent == null) {
            return "Student not found";
        }

        students.remove(student);

        return String.format("Removed student %s %s", currStudent.getFirstName(), currStudent.getLastName());
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (var student: students) {
            sb.append(String.format(
                    "==Student: First Name = %s, Last Name = %s, Best Subject = %s%n",
                    student.getFirstName(), student.getLastName(), student.getBestSubject()));
        }

        return  sb.toString().trim();
    }

    public int getStudentCount() {
       return students.size();
    }
}
