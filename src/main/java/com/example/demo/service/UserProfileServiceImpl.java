package com.example.demo.service;

import com.example.demo.model.UserProfileModel;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }


    @Override
    public List<UserProfileModel> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfileModel getUserProfileById(Long id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    @Override
    public UserProfileModel saveUserProfile(UserProfileModel userProfileModel) {
        return userProfileRepository.save(userProfileModel);
    }

    @Override
    public UserProfileModel updateUserProfile(UserProfileModel userProfileModel) {
        return userProfileRepository.save(userProfileModel);
    }

    @Override
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}
