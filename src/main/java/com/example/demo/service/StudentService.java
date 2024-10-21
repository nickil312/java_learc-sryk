package com.example.demo.service;

import com.example.demo.model.StudentModel;

import java.util.List;

public interface StudentService {
    public List<StudentModel> getStudents();

//    public List<StudentModel> getStudentsPage(int page, int size, String groupName, String faculty, String course, String search, boolean exist);

//    public int getTotalPages(int size, String groupName, String faculty, String course, String search, boolean exist);

    //    public StudentModel getStudentById(int id);
    public List<StudentModel> getStudentsByIds(List<Integer> studentIds);

    public StudentModel addStudent(StudentModel studentModel);

    public StudentModel updateStudent(StudentModel studentModel);

    public void deleteStudent(int id);
//    public void deleteStudentsLogical(List<Integer> ids);
//    public void deleteStudentsPhysical(List<Integer> ids);
}
