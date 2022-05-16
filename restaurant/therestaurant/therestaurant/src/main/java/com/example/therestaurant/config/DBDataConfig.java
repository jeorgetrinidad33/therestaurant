package com.example.therestaurant.config;

import com.example.therestaurant.entity.Category;
import com.example.therestaurant.entity.Menu;
import com.example.therestaurant.entity.User;
import com.example.therestaurant.repository.CategoryRepository;
import com.example.therestaurant.repository.MenuRepository;
import com.example.therestaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DBDataConfig {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    CommandLineRunner addUsers(UserRepository userRepository, CategoryRepository categoryRepository, MenuRepository menuRepository) {

        if(userRepository.findFirstByUsernameIsNotNull().isEmpty() && categoryRepository.findFirstByNameIsNotNull().isEmpty() && menuRepository.findFirstByNameIsNotNull().isEmpty()) {

            String password = bCryptPasswordEncoder.encode("admin123");
            return args -> {
                User test1 = new User("test1", "test", "one", "test1@test.com", "", "", password, User.isWho.Admin);

                userRepository.saveAll(
                        List.of(test1)
                );

                Category combos = new Category("Combos");
                Category appetizer = new Category("Appetizer");

                categoryRepository.saveAll(
                        List.of(combos, appetizer)
                );

                Menu menu1 = new Menu(combos, "Menu 1", "Sample menu", 15F, null, Menu.isAvailable.Available);
                Menu menu2 = new Menu(combos, "Menu 2", "Sample menu", 18F, null, Menu.isAvailable.Available);
                Menu menu3 = new Menu(combos, "Menu 3", "Sample menu", 22F, null, Menu.isAvailable.Available);
                Menu app1 = new Menu(appetizer, "Appetizer 1", "Sample menu", 8F, null, Menu.isAvailable.Available);
                Menu app2 = new Menu(appetizer, "Appetizer 2", "Sample menu", 5F, null, Menu.isAvailable.Available);
                Menu app3 = new Menu(appetizer, "Appetizer 3", "Sample menu", 12F, null, Menu.isAvailable.Available);

                menuRepository.saveAll(
                        List.of(menu1, menu2, menu3, app1, app2, app3)
                );
            };
        }
        return args -> {};
    }
}
