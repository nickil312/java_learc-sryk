package com.example.demo.repository;

import com.example.demo.model.StudentProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfileModel,Integer> {

}
