package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.Town;

public interface TownRepository extends JpaRepository<Town, Integer> {

}
