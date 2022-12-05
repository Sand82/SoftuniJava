package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
