package com.example.demo.service;

import com.example.demo.model.ModelUser;

import java.util.List;

public interface UserModelService {
    public List<ModelUser> getAllUsers();
    public ModelUser getUserById(long id);
    public ModelUser createUser(ModelUser user);
    public ModelUser updateUser(ModelUser user);
    public void deleteUser(long id);
}
