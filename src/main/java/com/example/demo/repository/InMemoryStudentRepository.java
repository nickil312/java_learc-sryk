package com.example.demo.repository;

import com.example.demo.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class InMemoryStudentRepository {

    private final List<StudentModel> STUDENTS = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);


    public List<StudentModel> getStudents() {
        return STUDENTS;
    }
//    public List<StudentModel> getStudentsPage(int page, int size) {
//        int startIndex = (page - 1) * size;
//        int endIndex = Math.min(startIndex + size, STUDENTS.size());
//        return new ArrayList<>(STUDENTS.subList(startIndex, endIndex));
//    }
public List<StudentModel> getStudentsPage(int page, int size, String groupName, String faculty, String course, String search,boolean exist) {
    List<StudentModel> students = new ArrayList<>();
    if (search != null && !search.isEmpty()) {
        students = STUDENTS.stream()
                .filter(s -> s.getName().contains(search) || s.getSecondName().contains(search))
                .collect(Collectors.toList());
    } else {
        students = STUDENTS;
    }

    if (groupName != null && !groupName.isEmpty()) {
        students = students.stream()
                .filter(s -> s.getGroupName().equals(groupName))
                .collect(Collectors.toList());
    }

    if (faculty != null && !faculty.isEmpty()) {
        students = students.stream()
                .filter(s -> s.getFaculty().equals(faculty))
                .collect(Collectors.toList());
    }

//    if (course != null && !course.isEmpty()) {
//        students = students.stream()
//                .filter(s -> s.getCourse().equals(course))
//                .collect(Collectors.toList());
//    }


    int startIndex = (page - 1) * size;
    int endIndex = Math.min(startIndex + size, students.size());
    return new ArrayList<>(students.subList(startIndex, endIndex));
}


    public int getTotalPages(int size, String groupName, String faculty, String course, String search,boolean exist) {
        List<StudentModel> students = getStudentsPage(1, Integer.MAX_VALUE, groupName, faculty, course, search,exist);
        return (int) Math.ceil((double) students.size() / size);
    }

    public void deleteStudentsLogical(List<Integer> studentIds) {
        for (int studentId : studentIds) {
            StudentModel student = getStudentById(studentId);
            if (student != null) {
                student.setExist(false); // Set the exist flag to false for logical deletion
            }
//            student.setExist(false); // set exist flag to false for logical deletion
//            STUDENTS.set(studentId,student);

        }
    }

    public void deleteStudentsPhysical(List<Integer> studentIds) {
        for (int studentId : studentIds) {
            deleteStudent(studentId); // physical deletion
        }
    }

//    public int getTotalPages(int size) {
//        return (int) Math.ceil((double) STUDENTS.size() / size);
//    }

    public StudentModel getStudentById(int id) {
        return STUDENTS.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    public StudentModel addStudent(StudentModel student) {
        student.setId(idCounter.getAndIncrement());
        STUDENTS.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student) {
        int StudentIndex = IntStream.range(0,STUDENTS.size()).filter(index -> STUDENTS.get(index).getId() == student.getId()).findFirst().orElse(-1);
        return StudentIndex == -1 ? null : STUDENTS.set(StudentIndex,student);
    }

    public void deleteStudent(int id) {
        var student = getStudentById(id);
        if(student == null) {
            return;
        }
        STUDENTS.remove(student);

    }
}
