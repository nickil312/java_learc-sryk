package com.example.demo.service;

import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentProfileModel;
import lombok.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface FacultyService {

    public List<FacultyModel> getFaculty();
    public FacultyModel createFaculty(FacultyModel student);
    public FacultyModel updateFaculty(FacultyModel student);
    public void deleteFaculty(Integer id);
    public FacultyModel getFacultyById(Integer id);
}
