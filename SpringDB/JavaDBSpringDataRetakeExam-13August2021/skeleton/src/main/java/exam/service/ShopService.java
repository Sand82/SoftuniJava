package exam.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface ShopService {

    boolean areImported();

    String readShopsFileContent() throws IOException;

    String importShops() throws JAXBException, FileNotFoundException;
}
