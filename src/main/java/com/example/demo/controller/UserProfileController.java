package com.example.demo.controller;

import com.example.demo.model.ModelUser;
import com.example.demo.model.UserProfileModel;
import com.example.demo.service.UserModelService;
import com.example.demo.service.UserProfileService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserModelService userModelService;

    @GetMapping("/userprofile")
    public String users(Model model) {
        List<ModelUser> users = userModelService.getAllUsers();
        List<UserProfileModel> usersprofiles = userProfileService.getAllUserProfiles();

//        model.addAttribute("users", users);
        model.addAttribute("usersprofiles", usersprofiles);
        model.addAttribute("usersprofile", new UserProfileModel()); // Pass a new CourseModel object

        return "userprofile";
    }

    //    @PostMapping("/userprofile/add")
//    public String addCource(@Valid @ModelAttribute UserProfileModel usersprofile, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Return to the form view with error messages
//            return "userprofile"; // Ensure this returns the correct view name
//        }
//
//
//        userProfileService.saveUserProfile(usersprofile);
//
//        return "redirect:/userprofile";
//    }
    @PostMapping("/userprofile/add")
    public String addCource(@Valid @ModelAttribute UserProfileModel usersprofile,
//                            @RequestParam Long userId,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userprofile"; // Возвращаем на ту же страницу с ошибками
        }

//        // Загрузите существующий ModelUser
//        ModelUser  user = userModelService.getUserById(userId);
//        if (user != null) {
//            usersprofile.setUser(user); // Установите связь с ModelUser
//        } else {
//            // Обработка случая, когда пользователь не найден
//            return "redirect:/userprofile"; // Или обработка ошибки
//        }

        userProfileService.saveUserProfile(usersprofile);
//            user.setUserProfile(usersprofile);
//        userModelService.updateUser(user); // Сохраните изменения в объекте user

        return "redirect:/userprofile";
    }

    @PostMapping("/userprofile/update")
    public String updateCource(@Valid @ModelAttribute UserProfileModel usersprofile,
//                               @RequestParam Long userId,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Возвращаем на ту же страницу с ошибками
            return "userprofile"; // Убедитесь, что это возвращает правильное имя представления
        }

//        // Загрузите существующий ModelUser  по идентификатору из usersprofile
//        ModelUser  user = userModelService.getUserById(userId);
//        if (user != null) {
//
//            user.setUserProfile(null);
//            userModelService.updateUser (user); // Сохраните изменения в объекте user
//
//
//            userProfileService.updateUserProfile(usersprofile);
//            user.setUserProfile(usersprofile);
//            userModelService.updateUser(user); // Сохраните изменения в объекте user
//
//
//
//
//
//        } else {
//            // Обработка случая, когда пользователь не найден
//            return "redirect:/userprofile"; // Или обработка ошибки
//        }
        userProfileService.updateUserProfile(usersprofile);

        return "redirect:/userprofile"; // Перенаправление после успешного обновления
    }

    @PostMapping("/userprofile/delete")
    public String deleteUser(@RequestParam long id) {
        userProfileService.deleteUserProfile(id);
        return "redirect:/userprofile";
    }
}
