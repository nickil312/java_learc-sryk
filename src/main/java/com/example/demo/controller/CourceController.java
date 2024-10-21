package com.example.demo.controller;

import com.example.demo.model.CourseModel;
import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.CourseNumberService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourceController {
    @Autowired
    private CourceService courceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseNumberService courseNumberService;

    @GetMapping("/course")
    public String getCourcesAll(Model model) {
        List<CourseNumberModel> courseNumbers = courseNumberService.getCourseNumber();
        List<CourseModel> courses = courceService.getCourcesAll();
        List<StudentModel> students = studentService.getStudents(); // Fetch all students

        model.addAttribute("courses", courses); // Use a different name for the list of courses
        model.addAttribute("courseNumber", courseNumbers);
        model.addAttribute("course", new CourseModel()); // Pass a new CourseModel object
        model.addAttribute("students", students); // Add students to the model

        System.out.println("course data: " + courses);
        return "course";
    }

    @PostMapping("/course/add")
    public String addCource(@Valid @ModelAttribute CourseModel course, BindingResult bindingResult,
                            @RequestParam(value = "studentIds", required = false) List<Integer> studentIds) {
        if (bindingResult.hasErrors()) {
            // Return to the form view with error messages
            return "course"; // Ensure this returns the correct view name
        }


        // Fetch students based on the selected IDs
        if (studentIds != null) {
            List<StudentModel> selectedStudents = studentService.getStudentsByIds(studentIds);
            course.setStudents(selectedStudents); // Set the selected students to the course
        }

        courceService.addCourse(course);
        return "redirect:/course";
    }


    @PostMapping("/course/update")
    public String updateCourse(
//    @RequestParam int id,
//                               @RequestParam String name,
//                               @RequestParam String description,
//                               @RequestParam String year,
//                               @RequestParam boolean exist,
//                               @RequestParam(value = "courseNumber.id") int courseNumberId,
//                               @RequestParam(value = "studentIds", required = false) List<Integer> studentIds) {
            @ModelAttribute CourseModel course,
            @RequestParam(value = "studentIds", required = false) List<Integer> studentIds){

        // Create a new CourseModel object with the updated data
//        CourseModel updatedCourse = new CourseModel();

        // Set the course number
//        CourseNumberModel courseNumber = courseNumberService.getCourseNumberById(courseNumberId);
//        updatedCourse.setCourseNumber(courseNumber);

        // Fetch students based on the selected IDs
        if (studentIds != null) {
            List<StudentModel> selectedStudents = studentService.getStudentsByIds(studentIds);
            course.setStudents(selectedStudents); // Set the selected students to the course
        }

        // Update the course in the database
        courceService.updateCourse(course);
        return "redirect:/course";
    }


    @PostMapping("/course/delete")
    public String deleteStudent(@RequestParam int id) {
        courceService.deleteCourse(id);
        return "redirect:/course";
    }
    @PostMapping("/course/multidelete")
    public String deleteStudents(@RequestParam("studentIds") List<Integer> studentIds, @RequestParam("deleteType") String deleteType) {
        if (deleteType.equals("Delete Logical")) {
            courceService.deleteCourseLogical(studentIds);
        } else if (deleteType.equals("Delete Physical")) {
            courceService.deleteCoursePhysical(studentIds);
        }
        System.out.println("ids" + studentIds);
        System.out.println("deleteType" + deleteType);

        return "redirect:/course";
    }

}
