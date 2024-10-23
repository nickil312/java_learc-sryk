package com.example.demo.controller;

import com.example.demo.model.CourseModel;
import com.example.demo.model.ModelUser;
import com.example.demo.model.StudentModel;
import com.example.demo.model.UserProfileModel;
import com.example.demo.service.UserModelService;
import com.example.demo.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserModelController {
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserProfileService userProfileService;


    @GetMapping("/users")
    public String users(Model model) {
        List<ModelUser> users = userModelService.getAllUsers();
        List<UserProfileModel> usersprofiles = userProfileService.getAllUserProfiles();

        model.addAttribute("usersprofiles", usersprofiles);
        model.addAttribute("users", users);
        model.addAttribute("user", new ModelUser()); // Pass a new CourseModel object
        return "users";
    }

    @PostMapping("/users/add")
    public String addCource(@Valid @ModelAttribute ModelUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return to the form view with error messages
            return "users"; // Ensure this returns the correct view name
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userModelService.createUser(user);
        return "redirect:/users";
    }
//    @PostMapping("/users/update")
//    public String updateCource(@Valid @ModelAttribute ModelUser user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Return to the form view with error messages
//            return "users"; // Ensure this returns the correct view name
//        }
//        System.out.println(user.getIdUser());
//
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userModelService.updateUser(user);
//        return "redirect:/users";
//    }
    @PostMapping("/users/update")
    public String updateCource(@Valid @ModelAttribute ModelUser  user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Возвращаем на ту же страницу с ошибками
            return "users"; // Убедитесь, что это возвращает правильное имя представления
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userModelService.updateUser (user);
        return "redirect:/users"; // Перенаправление на страницу пользователей
    }
    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam long id) {
        userModelService.deleteUser(id);
        return "redirect:/users";
    }
}
