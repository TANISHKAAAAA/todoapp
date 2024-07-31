package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/auth/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/auth/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/auth/register",method = RequestMethod.POST)
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "redirect:/login";
    }
    @RequestMapping(value = "/login_failure",method = RequestMethod.POST)
    public String handleFailedLogin(){
        System.out.print("Login Failed");
        return "redirect:/login?error=true";
    }


}
