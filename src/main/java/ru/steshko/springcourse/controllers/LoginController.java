package ru.steshko.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.steshko.springcourse.dao.UserDAO;
import ru.steshko.springcourse.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("person",userService.loadUserById(id));
        return "people/show";
    }
}
