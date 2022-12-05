package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.Plane;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {

}
