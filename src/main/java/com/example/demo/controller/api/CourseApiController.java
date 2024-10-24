package com.example.demo.controller.api;

import com.example.demo.model.CourseModel;
import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.CourseNumberService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/course")
public class CourseApiController {
    private final CourseNumberService courseNumberService;

    private final CourceService courceService;

    private final StudentService studentService;

    public CourseApiController(CourseNumberService courseNumberService, CourceService courceService, StudentService studentService) {
        this.courseNumberService = courseNumberService;
        this.courceService = courceService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<CourseModel> courseModelList(){
        return courceService.getCourcesAll();
    }

    @GetMapping("/{id}")
    public CourseModel facultyById(@PathVariable Integer id) {
        return courceService.getCourseById(id);
    }

    @PostMapping
    public CourseModel createStudent(@Valid @RequestBody CourseModel student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        System.out.println(student.getCourseNumber().getName());
        System.out.println(student.getCourseNumber().getId());
        System.out.println(student);


//        "faculty": {
//            "id": 2
//        },
        if (student.getCourseNumber() != null) {
            // Находим факультет по ID
            CourseNumberModel courseNumber = courseNumberService.getCourseNumberById(student.getCourseNumber().getId());
            if (courseNumber != null) {
                student.setCourseNumber(courseNumber); // Устанавливаем найденный факультет
            } else {
                System.out.println("courseNumber с ID " + student.getCourseNumber().getId() + " не найден.");
            }
        }
        // Обработка курсов
        if (student.getStudents() != null) {
            System.out.println(student.getStudents());

            List<StudentModel> courses = new ArrayList<>();
            for (StudentModel course : student.getStudents()) {
                System.out.println("student " + course.getId());

                StudentModel foundStudent = studentService.getStudentById(course.getId());
//                System.out.println("foundCourse " + foundCourse.getName());
                if (foundStudent != null) {
                    courses.add(foundStudent); // Добавляем найденный курс в список
                } else {
                    System.out.println("Курс с ID " + course.getId() + " не найден.");
                }
            }
            student.setStudents(courses); // Устанавливаем список курсов
        }

        return courceService.addCourse(student);
    }


    @PatchMapping("/{id}")
    public CourseModel updateStudent(@PathVariable int id, @Valid @RequestBody CourseModel student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return null; // Убедитесь, что это возвращает правильное имя представления
        }

        // Находим существующего студента по ID
        CourseModel existingCourse = courceService.getCourseById(id);
        if (existingCourse == null) {
            System.out.println("предмета с ID " + id + " не найден.");
            return null; // Или выбросьте исключение
        }


        // Обновление данных студента
        existingCourse.setName(student.getName());
        existingCourse.setDescription(student.getDescription());
        existingCourse.setYear(student.getYear());
        existingCourse.setExist(student.isExist());

        // Обработка факультета
        if (student.getCourseNumber() != null) {
            CourseNumberModel courseNumber = courseNumberService.getCourseNumberById(student.getCourseNumber().getId());
            if (courseNumber != null) {
                existingCourse.setCourseNumber(courseNumber); // Устанавливаем найденный факультет
            } else {
                System.out.println("курс с ID " + student.getCourseNumber().getId() + " не найден.");
            }
        }

        // Обработка курсов
        if (student.getStudents() != null) {
            List<StudentModel> studentModels = new ArrayList<>();
            for (StudentModel course : student.getStudents()) {
                System.out.println("student ID: " + course.getId());
                StudentModel foundStudent = studentService.getStudentById(course.getId());
                if (foundStudent != null) {
                    studentModels.add(foundStudent); // Добавляем найденный курс в список
                } else {
                    System.out.println("студент с ID " + course.getId() + " не найден.");
                }
            }
            existingCourse.setStudents(studentModels); // Устанавливаем список курсов
        }

        return courceService.updateCourse(existingCourse); // Сохраняем обновленного студента
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        courceService.deleteCourse(id);
    }


}
