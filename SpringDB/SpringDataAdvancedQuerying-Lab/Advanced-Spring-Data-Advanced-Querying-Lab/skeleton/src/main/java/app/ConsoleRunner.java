package app;

import app.model.enums.Size;
import app.model.shampoos.Shampoo;
import app.repositories.BasicShampooRepository;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static javax.swing.text.StyleConstants.Size;

@Component
public class ConsoleRunner  implements CommandLineRunner {

    private BasicShampooRepository shampooRepository;

    @Autowired
    public ConsoleRunner(BasicShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //findByBrand();

        Scanner scanner = new Scanner(System.in);

        this._01_findBySize(scanner);
    }

    private void _01_findBySize(Scanner scanner) {

        String input = scanner.nextLine();

        Size size = app.model.enums.Size.valueOf(input);

       List<Shampoo> shampoos = shampooRepository.findBySizeOrderByIdAsc(size);

       shampoos.forEach(ch -> System.out.println(ch.getBrand() + " " + ch.getSize() +  " " + ch.getPrice() + "lv."));
    }

    private void findByBrand() {

        List<Shampoo> shampoos = this.shampooRepository.findByBrand("Cotton Fresh");

        shampoos.forEach(sh -> System.out.println(sh.getBrand() + " -> " + sh.getPrice()));
    }
}
