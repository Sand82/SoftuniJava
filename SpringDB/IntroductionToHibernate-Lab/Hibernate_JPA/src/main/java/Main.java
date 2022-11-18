import entities.Student;
import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

//        Student student = new Student("sand", 40);
//        entityManager.persist(student);
        Teacher teacher = new Teacher("Pesho");
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
