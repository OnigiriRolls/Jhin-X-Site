package com.jhinx.GamingSite.service;

import com.jhinx.GamingSite.model.User;
import com.jhinx.GamingSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean existsById(Long id)
    {
        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        List<User> users = userRepository.findAll();

        return users.stream().map((u)->u.getUsername())
                .filter((u)->u.equals(username)).findFirst().isPresent();
    }

    public boolean existsByEmail(String email){
        List<User> users = userRepository.findAll();

        return users.stream().map((u)->u.getEmailId())
                .filter((u)->u.equals(email)).findFirst().isPresent();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        List<User> users = userRepository.findAll();
        return users.stream().filter((u)->u.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean checkLogIn(String email, String password) {
        List<User> users = userRepository.findAll();

        System.out.println(email+" " +password);

        Optional<User> emailCheck = users.stream().filter((u) -> u.getEmailId().trim().equals(email)).findFirst();

        if (emailCheck.isPresent())
        {
            System.out.println(emailCheck.get().getPassword());
            return true;
        }
        return false;
    }
}
