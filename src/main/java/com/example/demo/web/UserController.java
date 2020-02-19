package com.example.demo.web;

import com.example.demo.models.Medicine;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        user.setUsername(username);
        user.setPassword(password);
        return userService.save(user);
    }
}
