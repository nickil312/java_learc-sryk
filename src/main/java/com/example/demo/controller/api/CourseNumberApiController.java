package com.example.demo.controller.api;

import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourseNumberService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/coursenumber")
public class CourseNumberApiController {

    private final CourseNumberService courseNumberService;


    public CourseNumberApiController(CourseNumberService courseNumberService) {
        this.courseNumberService = courseNumberService;
    }

    @GetMapping
    public List<CourseNumberModel> getCourseNumbers() {
        return courseNumberService.getCourseNumber();
    }

    @GetMapping("/{id}")
    public CourseNumberModel CourseNumberById(@PathVariable Integer id) {
        return courseNumberService.getCourseNumberById(id);
    }

    @PostMapping
    public CourseNumberModel createStudent(@Valid @RequestBody CourseNumberModel student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return null; // Ensure this returns the correct view name
        }

        return courseNumberService.createCourseNumber(student);
    }
    @PatchMapping("/{id}")
    public CourseNumberModel updateStudent(@Valid @PathVariable Integer id, @RequestBody CourseNumberModel student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return null; // Ensure this returns the correct view name
        }
        student.setId(id);
        return courseNumberService.updateCourseNumber(student);
    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        courseNumberService.deleteCourseNumber(id);
    }

}
