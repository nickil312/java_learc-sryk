package com.example.demo.service;

import com.example.demo.model.StudentProfileModel;

import java.util.List;

public interface StudentProfileService {
    public List<StudentProfileModel> getStudentsProfiles();
    public StudentProfileModel createStudentProfile(StudentProfileModel student);
    public StudentProfileModel updateStudentProfile(StudentProfileModel student);
    public void deleteStudentProfile(Integer id);
}
