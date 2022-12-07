package com.example.mobilelele.init;

import com.example.mobilelele.model.entities.BrandEntity;
import com.example.mobilelele.model.entities.CategoryEnum;
import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.repositories.BrandRepository;
import com.example.mobilelele.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private BrandRepository brandRepository;

    @Autowired
    public DbInit(BrandRepository brandRepository) {

        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (brandRepository.count() == 0) {

            BrandEntity ford = new BrandEntity().setName("Ford");

            ModelEntity fiesta = new ModelEntity().setName("Fiesta")
                    .setImageUrl("https://www.motopfohe.bg/files/news/archive/2017/08/blob-server.jpg")
                    .setStartYear(2017)
                    .setEndYear(null)
                    .setCategory(CategoryEnum.CAR);

            ModelEntity escort = new ModelEntity().setName("Escort")
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/3/39/Ford_Escort_RS2000_MkI.jpg")
                    .setStartYear(1968)
                    .setEndYear(2002)
                    .setCategory(CategoryEnum.CAR);

            ford.setModels(List.of(fiesta, escort));

            fiesta.setBrand(ford);
            escort.setBrand(ford);

            brandRepository.save(ford);
        }
    }
}
