package com.example.demo.controller;


import com.example.demo.model.ModelUser;
import com.example.demo.model.RoleEnum;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
//
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/regis")
    public String regView() {
        return "regis";
    }

    @PostMapping("/regis")
    public String reg( ModelUser user, Model model) {
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }
        if (!isValidPassword(user.getPassword())) {
            model.addAttribute("message1", "Пароль должен быть не менее 3 символов, содержать хотя бы одну цифру, одну букву и один специальный символ");
            return "regis";
        }


        // Set roles based on the username
        if ("admin".equals(user.getUsername())) {
            user.setRoles(Collections.singleton(RoleEnum.ADMIN)); // Set role to ADMIN
            System.out.println("admin");

        } else {
            user.setRoles(Collections.singleton(RoleEnum.USER)); // Set role to USER
            System.out.println("user");
        }

        user.setActive(true);
        System.out.println(user.getUsername());
        System.out.println("user");

//        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setPassword(user.getPassword());
        userRepository.save(user);
        return "redirect:/login";
    }

    private boolean isValidPassword(String password) {
        // Проверка длины пароля
        if (password.length() < 3) {
            return false;
        }
        // Проверка наличия хотя бы одного специального символа
        String specialCharacters = "!@#$%^&*()-_=+[]{};:'\",.<>?/|\\`~";
        for (char c : specialCharacters.toCharArray()) {
            if (password.indexOf(c) >= 0) {
                return true; // Найден специальный символ
            }
        }
        return false; // Специальный символ не найден
    }
}
