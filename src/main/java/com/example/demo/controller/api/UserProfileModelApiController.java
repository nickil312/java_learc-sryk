package com.example.demo.controller.api;

import com.example.demo.model.UserProfileModel;
import com.example.demo.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/student_profiles")
public class UserProfileModelApiController {
    private final UserProfileService userProfileService;

    public UserProfileModelApiController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    @GetMapping
    public List<UserProfileModel> getUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }
    @GetMapping("/{id}")
    public UserProfileModel getUserProfileById(@PathVariable Long id){
        return userProfileService.getUserProfileById(id);
    }
    @PostMapping
    public UserProfileModel createUserProfile(@Valid  @RequestBody UserProfileModel userProfileModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        return userProfileService.saveUserProfile(userProfileModel);
    }
    @PatchMapping("/{id}")
    public UserProfileModel createUserProfile(@Valid @PathVariable Long id, @RequestBody UserProfileModel userProfileModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        userProfileModel.setId(id);
        return userProfileService.saveUserProfile(userProfileModel);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
    }
}
