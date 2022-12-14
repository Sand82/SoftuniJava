package com.example.gamestore;

import com.example.gamestore.entities.exceptions.ValidationException;
import com.example.gamestore.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ExecutorService executorService;
    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String result = " ";

        while(!result.equals("End")) {

            try{

                result = executorService.execute(scanner.nextLine());

            }catch (ValidationException ex) {

                result = ex.getMessage();
            }

            System.out.println(result);
        }
    }
}
