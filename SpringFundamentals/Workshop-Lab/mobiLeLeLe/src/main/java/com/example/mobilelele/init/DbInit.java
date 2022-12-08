package com.example.mobilelele.init;

import com.example.mobilelele.model.entities.BrandEntity;
import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.model.entities.UserRoleEntity;
import com.example.mobilelele.model.enums.CategoryEnum;
import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.model.enums.RoleEnum;
import com.example.mobilelele.repositories.BrandRepository;
import com.example.mobilelele.repositories.UserRepository;
import com.example.mobilelele.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private BrandRepository brandRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(BrandRepository brandRepository,
                  UserRepository userRepository,
                  UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {

        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (brandRepository.count() == 0) {

            BrandEntity ford = new BrandEntity().setName("Ford");
            BrandEntity honda = new BrandEntity().setName("Honda");
            BrandEntity opel = new BrandEntity().setName("Opel");


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

            ModelEntity nc750s = new ModelEntity().setName("NC750S")
                    .setImageUrl("https://cdn.visordown.com/article-images/8/82383.png")
                    .setStartYear(2014)
                    .setEndYear(null)
                    .setCategory(CategoryEnum.MOTORCYCLE);

            ford.setModels(List.of(fiesta, escort, nc750s));

            fiesta.setBrand(ford);
            escort.setBrand(ford);

            brandRepository.saveAll(List.of(ford, opel, honda));
        }

        if (userRoleRepository.count() == 0) {

            UserRoleEntity admin = new UserRoleEntity().setRole(RoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity().setRole(RoleEnum.USER);

            userRoleRepository.saveAll(List.of(admin, user));
        }

        if (userRepository.count() == 0) {

            UserEntity admin = new UserEntity()
                    .setFirstName("Sand")
                    .setLastName("Stef")
                    .setUsername("Sand82")
                    .setPassword(passwordEncoder.encode("123456"))
                    .setActive(true)
                    .setImageUrl("https://img.freepik.com/free-photo/portrait-successful-man-having-stubble-posing-with-broad-smile-keeping-arms-folded_171337-1267.jpg?w=2000")
                    .setUserRoles(List.of(userRoleRepository.findById(1l).get(), userRoleRepository.findById(2l).get()));

            UserEntity user = new UserEntity()
                    .setFirstName("Mimeto")
                    .setLastName("Stef")
                    .setUsername("Mimi")
                    .setPassword(passwordEncoder.encode("123456"))
                    .setActive(true)
                    .setImageUrl("https://images.pexels.com/photos/38554/girl-people-landscape-sun-38554.jpeg?auto=compress&cs=tinysrgb&w=600")
                    .setUserRoles(List.of(userRoleRepository.findById(1l).get()));

            userRepository.saveAll(List.of(admin, user));
        }
    }


}
