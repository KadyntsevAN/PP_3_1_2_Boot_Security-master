package ru.kata.spring.boot_security.demo.services;




import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    public void save(User user, String[] roleNames);
    public List<User> show();
    public void delete(int id);
    public void update(int id, User updateUser);
    public User find(int id);
    public User find(String name);

}
