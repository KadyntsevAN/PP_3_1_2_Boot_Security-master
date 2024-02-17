package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public List<User> show();
    public void delete(int id);
    public void update(int id, User updateUser);
    public User find(int id);
    public User find(String name);


}
