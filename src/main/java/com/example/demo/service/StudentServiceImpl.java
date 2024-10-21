package com.example.demo.service;

import com.example.demo.model.StudentModel;
import com.example.demo.repository.InMemoryStudentRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService {

    public final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public final InMemoryStudentRepository inMemoryStudentRepository;

//    public StudentServiceImpl(StudentRepository studentRepository, InMemoryStudentRepository inMemoryStudentRepository) {
//        this.studentRepository = studentRepository;
////        this.inMemoryStudentRepository = inMemoryStudentRepository;
//    }

    @Override
    public List<StudentModel> getStudents() {
        return studentRepository.findAll();
    }

//    @Override
//    public List<StudentModel> getStudentsPage(int page,int size, String groupName, String faculty, String course, String search,boolean exist) {
//        return inMemoryStudentRepository.getStudentsPage(page,size,groupName,faculty,course,search,exist);
//    }

//    @Override
//    public int getTotalPages(int size, String groupName, String faculty, String course, String search,boolean exist) {
//        return inMemoryStudentRepository.getTotalPages(size,groupName,faculty,course,search,exist);
//    }

    public List<StudentModel> getStudentsByIds(List<Integer> studentIds) {
        return studentRepository.findAllById(studentIds); // Assuming you have a method in your repository
    }

    @Override
    public StudentModel addStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    @Override
    public StudentModel updateStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

//    @Override
//    public void deleteStudentsLogical(List<Integer> ids) {
//        inMemoryStudentRepository.deleteStudentsLogical(ids);
//    }
//
//    @Override
//    public void deleteStudentsPhysical(List<Integer> ids) {
//        inMemoryStudentRepository.deleteStudentsPhysical(ids);
//    }
}
