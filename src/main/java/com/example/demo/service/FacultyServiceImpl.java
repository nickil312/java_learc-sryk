package com.example.demo.service;

import com.example.demo.model.FacultyModel;
import com.example.demo.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<FacultyModel> getFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public FacultyModel createFaculty(FacultyModel student) {
        return facultyRepository.save(student);
    }

    @Override
    public FacultyModel updateFaculty(FacultyModel student) {
        return facultyRepository.save(student);
    }

    @Override
    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyModel getFacultyById(Integer id) {
        return facultyRepository.findById(id).orElse(null);
    }
}
