package com.example.demo.repository;

import com.example.demo.model.FacultyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyModel,Integer> {
}
