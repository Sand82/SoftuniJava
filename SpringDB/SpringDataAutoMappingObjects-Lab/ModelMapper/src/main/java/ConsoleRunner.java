import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee manager = new Employee("Sand", "stef", BigDecimal.TEN, LocalDate.now(), null);
        Employee employeeOne = new Employee("Mish", "Stef", BigDecimal.TEN, LocalDate.now(), manager);
        Employee employeeTwo = new Employee("Lub", "Stef", BigDecimal.ONE, LocalDate.now(), manager);

        this.employeeService.save(manager);

    }
}
