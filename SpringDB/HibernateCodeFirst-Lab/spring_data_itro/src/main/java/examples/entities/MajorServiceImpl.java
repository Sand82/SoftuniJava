package examples.entities;

import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {
    @Override
    public void register() {
        System.out.println("Major service");
    }
}
