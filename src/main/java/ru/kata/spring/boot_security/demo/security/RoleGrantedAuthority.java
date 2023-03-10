package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

public class RoleGrantedAuthority implements GrantedAuthority {

    private final Role role;

    public RoleGrantedAuthority(Role role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
