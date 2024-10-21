package com.example.demo.service;

import com.example.demo.model.StudentModel;
import com.example.demo.repository.InMemoryStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService {

    public final InMemoryStudentRepository inMemoryStudentRepository;

    public StudentServiceImpl(InMemoryStudentRepository inMemoryStudentRepository) {
        this.inMemoryStudentRepository = inMemoryStudentRepository;
    }

    @Override
    public List<StudentModel> getStudents() {
        return inMemoryStudentRepository.getStudents();
    }

    @Override
    public List<StudentModel> getStudentsPage(int page,int size, String groupName, String faculty, String course, String search,boolean exist) {
        return inMemoryStudentRepository.getStudentsPage(page,size,groupName,faculty,course,search,exist);
    }

    @Override
    public int getTotalPages(int size, String groupName, String faculty, String course, String search,boolean exist) {
        return inMemoryStudentRepository.getTotalPages(size,groupName,faculty,course,search,exist);
    }

    @Override
    public StudentModel getStudentById(int id) {
        return inMemoryStudentRepository.getStudentById(id);
    }

    @Override
    public StudentModel addStudent(StudentModel studentModel) {
        return inMemoryStudentRepository.addStudent(studentModel);
    }

    @Override
    public StudentModel updateStudent(StudentModel studentModel) {
        return inMemoryStudentRepository.updateStudent(studentModel);
    }

    @Override
    public void deleteStudent(int id) {
        inMemoryStudentRepository.deleteStudent(id);
    }

    @Override
    public void deleteStudentsLogical(List<Integer> ids) {
        inMemoryStudentRepository.deleteStudentsLogical(ids);
    }

    @Override
    public void deleteStudentsPhysical(List<Integer> ids) {
        inMemoryStudentRepository.deleteStudentsPhysical(ids);
    }
}
