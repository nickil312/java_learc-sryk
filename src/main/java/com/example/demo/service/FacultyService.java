package com.example.demo.service;

import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentProfileModel;

import java.util.List;

public interface FacultyService {
    public List<FacultyModel> getFaculty();
    public FacultyModel createFaculty(FacultyModel student);
    public FacultyModel updateFaculty(FacultyModel student);
    public void deleteFaculty(Integer id);
}
