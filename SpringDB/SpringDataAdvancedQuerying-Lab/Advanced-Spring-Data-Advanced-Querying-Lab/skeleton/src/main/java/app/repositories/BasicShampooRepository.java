package app.repositories;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicShampooRepository extends JpaRepository<BasicShampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findBySizeOrderByIdAsc(Size size);
}
