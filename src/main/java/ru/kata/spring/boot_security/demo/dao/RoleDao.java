package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    public List<Role> show();

    void delete(Role role);
    Role find(int id);
    Role find(String role);
}
