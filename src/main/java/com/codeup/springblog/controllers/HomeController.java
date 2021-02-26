package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final UserRepository userDao;
    private final PasswordEncoder encoder;

    public HomeController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @GetMapping("/")
    @ResponseBody
    public String landingPage(){
        return "This is the landing page!";
    }

    //Post Mapping for login is handled by Spring Security
    @GetMapping("/login")
    public String showLoginForm(){
        return "/posts/login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "/posts/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        String password = user.getPassword();
        String hash = encoder.encode(password);
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}
