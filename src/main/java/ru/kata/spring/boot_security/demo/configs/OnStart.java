package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class OnStart implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");


        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");

        Collection<Role> userRoles = List.of(userRole);
        Collection<Role> adminRoles = List.of(adminRole);
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setFirst_name("user");
        user.setLast_name("user");
        user.setAge(23);
        user.setEmail("user@mail.ru");
        user.setAuthority(userRoles);
        userRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirst_name("admin");
        admin.setLast_name("admin");
        admin.setAge(29);
        admin.setEmail("admin@mail.ru");
        admin.setAuthority(adminRoles);

        System.out.println("this is the ADMIN AAA role ->>>>" + adminRole.getRole());
        for (Role role : userRoles) {
            System.out.println(role.getRole());
        }

        userRepository.save(admin);

    }
}
