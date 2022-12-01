package exam.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface LaptopService {
    boolean areImported();

    String readLaptopsFileContent() throws IOException;

    String importLaptops() throws IOException;

    String exportBestLaptops();
}
