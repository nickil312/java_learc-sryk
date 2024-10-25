package com.example.demo.controller.api;

import com.example.demo.model.ModelUser;
import com.example.demo.model.RoleEnum;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
public class UserModelApiController {
    private final UserModelService userModelService;

    @Autowired
    private UserRepository userRepository;
    //
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModelApiController(UserModelService userModelService) {
        this.userModelService = userModelService;
    }

    @GetMapping
    public List<ModelUser> getUsers() {
        return userModelService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ModelUser getUser(@PathVariable Long id) {
        return userModelService.getUserById(id);
    }

    @PostMapping
    public ModelUser createUser(@Valid @RequestBody ModelUser modelUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        return userModelService.createUser(modelUser);
    }

    //    @PostMapping("/auth")
//    public ModelUser auth(@Valid @RequestBody ModelUser modelUser, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
//        return null;
//    }
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@Valid @RequestBody ModelUser modelUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body("Invalid input");
        }

        // Проверяем, существует ли пользователь
        ModelUser existingUser = userRepository.findByUsername(modelUser.getUsername());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User  not found");
        }

        // Проверяем правильность пароля
        if (!passwordEncoder.matches(modelUser.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Если авторизация успешна, возвращаем информацию о пользователе
        return ResponseEntity.ok(existingUser);
    }

    @PostMapping("/regis")
    public ResponseEntity<?> reg(@RequestBody ModelUser user, Model model, BindingResult bindingResult) {
        if (userRepository.existsByUsername(user.getUsername())) {
//            model.addAttribute("message", "Пользователь с таким логином уже существует");
//            return "regis";
            return ResponseEntity.badRequest().body("Invalid input: " + bindingResult.getAllErrors().get(0).getDefaultMessage());

        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с таким логином уже существует");
        }

        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        if (!isValidPassword(user.getPassword())) {
//            model.addAttribute("message1", "Пароль должен быть не менее 3 символов, содержать хотя бы одну цифру, одну букву и один специальный символ");
            return ResponseEntity.badRequest().body("Пароль должен быть не менее 3 символов, содержать хотя бы одну цифру, одну букву и один специальный символ");

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
//        return "redirect:/login";
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");

    }


    @PatchMapping("/{id}")
    public ModelUser updateUser(@Valid @PathVariable Long id, @RequestBody ModelUser modelUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        modelUser.setIdUser(id);
        return userModelService.createUser(modelUser);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        userModelService.deleteUser(id);
    }


    private boolean isValidPassword(String password) {
        // Проверка длины пароля
        System.out.println(password);

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
