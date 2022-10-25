package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/")
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @PostMapping("/")
    public ModelAndView save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("/index");
        }
        else {
            userService.save(user);
            ModelAndView modelAndView = new ModelAndView("/result");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
    }
}
