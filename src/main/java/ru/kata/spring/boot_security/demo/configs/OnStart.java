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
import java.util.List;

@Component
public class OnStart implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");


        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");

        List<Role> userRoles = Arrays.asList(userRole);
        List<Role> adminRoles = Arrays.asList(adminRole);
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



        userRepository.save(admin);

    }
}
