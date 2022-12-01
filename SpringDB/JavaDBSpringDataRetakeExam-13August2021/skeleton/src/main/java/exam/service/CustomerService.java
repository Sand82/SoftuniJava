package exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CustomerService {

    boolean areImported();

    String readCustomersFileContent() throws IOException;

    String importCustomers() throws IOException;

}
