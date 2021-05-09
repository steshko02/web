package ru.steshko.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.steshko.springcourse.dao.UserDAO;
import ru.steshko.springcourse.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String index(Model model){
        //получим всех людей из Dao и передадим в предстовление
        model.addAttribute("people",userService.allUsers());
        return "people/index";
    }

}
