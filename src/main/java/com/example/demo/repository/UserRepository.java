package com.example.demo.repository;

import com.example.demo.model.ModelUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ModelUser, Long> {
    ModelUser findByUsername(String username);
    boolean existsByUsername(String username);
}
