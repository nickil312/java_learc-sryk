package com.example.demo.controller.api;

import com.example.demo.model.CourseModel;
import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.FacultyService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//
//{
//        "name":"stud111",
//        "secondName":"stud13",
//        "groupName":"Group A",
//        "exist":true,
//        "faculty":{
//        "id":2
//        },
//        "courses":[
//        {
//        "id":1
//        },
//        {
//        "id":2
//        }
//        ]
//
//        }


@RestController
@RequestMapping("/v1/api/students")
public class StudentApiController {
    private final StudentService studentService;
    private final FacultyService facultyService;
    private final CourceService courceService;

    public StudentApiController(StudentService studentService, FacultyService facultyService, CourceService courceService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.courceService = courceService;
    }

    @GetMapping
    public List<StudentModel> getStudents() {
        return studentService.getStudents();
    }
    @GetMapping("/{id}")
    public StudentModel StudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentModel createStudent(@Valid @RequestBody StudentModel student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        System.out.println(student.getFaculty().getName());
        System.out.println(student.getFaculty().getId());
        System.out.println(student);


//        "faculty": {
//            "id": 2
//        },
        if (student.getFaculty() != null) {
            // Находим факультет по ID
            FacultyModel faculty = facultyService.getFacultyById(student.getFaculty().getId());
            if (faculty != null) {
                student.setFaculty(faculty); // Устанавливаем найденный факультет
            } else {
                System.out.println("Факультет с ID " + student.getFaculty().getId() + " не найден.");
            }
        }
        // Обработка курсов
        if (student.getCourses() != null) {
            System.out.println(student.getCourses());

            List<CourseModel> courses = new ArrayList<>();
            for (CourseModel course : student.getCourses()) {
                System.out.println("course " + course.getId());

                CourseModel foundCourse = courceService.getCourseById(course.getId());
//                System.out.println("foundCourse " + foundCourse.getName());
                if (foundCourse != null) {
                    courses.add(foundCourse); // Добавляем найденный курс в список
                } else {
                    System.out.println("Курс с ID " + course.getId() + " не найден.");
                }
            }
            student.setCourses(courses); // Устанавливаем список курсов
        }

        return studentService.addStudent(student);
    }

    @PatchMapping("/{id}")
    public StudentModel updateStudent(@PathVariable int id, @Valid @RequestBody StudentModel student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return null; // Убедитесь, что это возвращает правильное имя представления
        }

        // Находим существующего студента по ID
        StudentModel existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            System.out.println("Студент с ID " + id + " не найден.");
            return null; // Или выбросьте исключение
        }

        // Обновление данных студента
        existingStudent.setName(student.getName());
        existingStudent.setSecondName(student.getSecondName());
        existingStudent.setGroupName(student.getGroupName());
        existingStudent.setExist(student.isExist());

        // Обработка факультета
        if (student.getFaculty() != null) {
            FacultyModel faculty = facultyService.getFacultyById(student.getFaculty().getId());
            if (faculty != null) {
                existingStudent.setFaculty(faculty); // Устанавливаем найденный факультет
            } else {
                System.out.println("Факультет с ID " + student.getFaculty().getId() + " не найден.");
            }
        }

        // Обработка курсов
        if (student.getCourses() != null) {
            List<CourseModel> courses = new ArrayList<>();
            for (CourseModel course : student.getCourses()) {
                System.out.println("course ID: " + course.getId());
                CourseModel foundCourse = courceService.getCourseById(course.getId());
                if (foundCourse != null) {
                    courses.add(foundCourse); // Добавляем найденный курс в список
                } else {
                    System.out.println("Курс с ID " + course.getId() + " не найден.");
                }
            }
            existingStudent.setCourses(courses); // Устанавливаем список курсов
        }

        return studentService.updateStudent(existingStudent); // Сохраняем обновленного студента
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
