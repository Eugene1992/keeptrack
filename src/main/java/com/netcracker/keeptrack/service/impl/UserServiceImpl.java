package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} interface that provides methods for Task
 * entity business logic.
 *
 * @see User
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void addUser(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Integer id) {
        repository.delete(id);
    }

    @Override
    public User getUserById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void editUser(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public List<User> getFreeEmployees() {
        return repository.getFreeEmployees();
    }

    @Override
    public List<User> getFreeManagers() {
        return repository.getFreeManagers();
    }
}
