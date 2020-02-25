package com.example.demo.web;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
@RequestMapping("/user")
public class UserController {

    private  UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/add")
    @ResponseBody
    public User add(@RequestParam
                                String username,@RequestParam String password) {

        User user=new User();
        Optional<User> testUsername = userService.findByUsername(username);
        if (testUsername.isPresent()) {
            System.out.println("Postoi takov korisnik");
            return user;
        } else {
            user.setUsername(username);
            user.setPassword(String.valueOf(password.hashCode()));
            return userService.save(user);
        }
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public User login(@RequestParam
                            String username,@RequestParam String password) {
        password=String.valueOf(password.hashCode());
            return userService.findByUsernameAndPassword(username, password).orElse(null);

    }
}
