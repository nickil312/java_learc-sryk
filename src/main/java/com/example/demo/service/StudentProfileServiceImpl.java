package com.example.demo.service;

import com.example.demo.model.StudentProfileModel;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

//    public final InMemoryStudentProfileRepository inMemoryStudentProfileRepository;
//
//    public StudentProfileServiceImpl(InMemoryStudentRepository inMemoryStudentRepository, InMemoryStudentProfileRepository inMemoryStudentProfileRepository) {
//        this.inMemoryStudentProfileRepository = inMemoryStudentProfileRepository;
//    }
//
//
//    @Override
//    public List<StudentProfileModel> getStudentsProfiles() {
//        return inMemoryStudentProfileRepository.getStudentsProfiles();
//    }
//
//    @Override
//    public StudentProfileModel createStudentProfile(StudentProfileModel student) {
//        return inMemoryStudentProfileRepository.createStudentProfile(student);
//    }
    public final StudentProfileRepository studentProfileRepository;


    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public List<StudentProfileModel> getStudentsProfiles() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfileModel createStudentProfile(StudentProfileModel student) {
        return studentProfileRepository.save(student);
    }

    @Override
    public StudentProfileModel updateStudentProfile(StudentProfileModel student) {
        return studentProfileRepository.save(student);
    }

    @Override
    public void deleteStudentProfile(Integer id) {
        studentProfileRepository.deleteById(id);
    }
}
