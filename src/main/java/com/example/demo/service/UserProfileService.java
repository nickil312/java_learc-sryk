package com.example.demo.service;

import com.example.demo.model.UserProfileModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserProfileService {
    public List<UserProfileModel> getAllUserProfiles();
    public UserProfileModel getUserProfileById(Long id);
    public UserProfileModel saveUserProfile(UserProfileModel userProfileModel);
    public UserProfileModel updateUserProfile(UserProfileModel userProfileModel);
    public void deleteUserProfile(Long id);
}
