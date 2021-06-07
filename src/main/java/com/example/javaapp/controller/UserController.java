package com.example.javaapp.controller;

import java.util.List;

import com.example.javaapp.Entity.User;
import com.example.javaapp.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    // home
    @RequestMapping(value = ("/"), method = RequestMethod.GET)
    public String viewHomePage(){
        return "index";
    }

    // register form
    @RequestMapping(value = ("/register"), method = RequestMethod.GET)
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    // process register
    @RequestMapping(value = ("/process_register"), method = RequestMethod.POST)
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        List<User> listUser = repo.findAll();
        for(User item:listUser){
            if(user.getEmail().equals(item.getEmail())){
                return "regist_faile";
            }
        }
        
        repo.save(user);
        return "regist_success";
    }
}
