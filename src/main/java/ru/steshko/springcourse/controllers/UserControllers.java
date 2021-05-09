package ru.steshko.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.steshko.springcourse.dao.UserDAO;
import ru.steshko.springcourse.models.User;
import ru.steshko.springcourse.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class UserControllers {

    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/news")
    public String newsShow(){
        //олучаем 1 чела по ид и передаем на отображение в представление
        return "people/news";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        //олучаем 1 чела по ид и передаем на отображение в представление
        model.addAttribute("person",userService.loadUserById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String  newPerson(@ModelAttribute("person") User person){//Model model
       // model.addAttribute("person",new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User person,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";

        userService.saveUser(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("person",userService.loadUserById(id));
                return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User person,  BindingResult bindingResult,
                         @PathVariable("id") int id){

        if(bindingResult.hasErrors())
            return "people/edit";

        userService.updateUser(id,person);
        return "redirect:/people/{id}";
    }

}
