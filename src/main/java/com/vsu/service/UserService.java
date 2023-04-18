package com.vsu.service;

import com.vsu.entities.User;
import com.vsu.exception.ValidationException;
import com.vsu.repository.UserRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private final UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = userRepository.selectByEmail(login);
        if (user == null) {
            LOGGER.log(Level.INFO, "User with login {0} is not exist", login);
            throw new ValidationException("User is not found");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        LOGGER.log(Level.WARNING, "Something wrong with input data");
        throw new ValidationException("Bad credentials");
    }

    public User insertUser(User user) {
        if (userRepository.insert(user) < 1) {
            LOGGER.log(Level.INFO,"User with id {0} is not inserted", user.getId());
            return null;
        } else {
            return userRepository.selectByEmail(user.getEmail());
        }
    }

    public void deleteUser(String id) {
        try {
            Long idL = Long.parseLong(id);
            if (userRepository.deleteById(idL) < 1) {
                LOGGER.log(Level.INFO, "User with id {0} is not deleted", id);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING,"ID {0} has wrong type", id);
            throw new ValidationException("Bad ID");
        }

    }

    public User selectByIdUser(String id) {
        try {
            Long idL = Long.parseLong(id);
            return userRepository.selectById(idL);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING,"ID {0} has wrong type", id);
            throw new ValidationException("Bad ID");
        }

    }

    public void updateByID(User user) {
        if(userRepository.updateByID(user) < 1){
            LOGGER.log(Level.INFO,"User with id {} is not updated", user.getId());
        }
    }
}
