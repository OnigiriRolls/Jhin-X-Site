package com.jhinx.GamingSite.controllers;

import com.jhinx.GamingSite.exceptions.UserNotFoundException;
import com.jhinx.GamingSite.model.User;
import com.jhinx.GamingSite.service.UserService;
import com.mysql.cj.xdevapi.JsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jhinx.GamingSite.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @PostMapping("/logIn")
    public boolean logIn(@RequestBody String body, HttpServletResponse response) {
        String[] aux = body.split(";");
        String emailAux = aux[0];
        String passAux = aux[1];
        emailAux = emailAux.trim();
        passAux = passAux.trim();
        String email=emailAux.substring(1);
        String pass=passAux.substring(0, passAux.length()-1);

        if(!email.isEmpty() && !pass.isEmpty()) {
            System.out.println("email=" + email + "  " + "pass=" + pass);

            boolean check = userService.checkLogIn(email, pass);

            if (check == true) {
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.saveUser(user);
        return "New User Added";
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userService.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userService.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }
}
