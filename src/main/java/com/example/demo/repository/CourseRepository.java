package com.example.demo.repository;

import com.example.demo.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseModel,Integer> {
}