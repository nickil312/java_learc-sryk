package com.example.demo.service;

import com.example.demo.model.ModelUser;
import com.example.demo.repository.UserModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserModelServiceImpl implements UserModelService {

    public final UserModelRepository userModelRepository;

    public UserModelServiceImpl(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }



    @Override
    public List<ModelUser> getAllUsers() {
        return userModelRepository.findAll();
    }

    @Override
    public ModelUser getUserById(long id) {
        return userModelRepository.getReferenceById(id);
    }

    @Override
    public ModelUser createUser(ModelUser user) {
        return userModelRepository.save(user);
    }

    @Override
    public ModelUser updateUser(ModelUser user) {
        return userModelRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userModelRepository.deleteById(id);
    }
}
