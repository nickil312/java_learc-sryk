package com.example.demo.controller;

import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.FacultyModel;
import com.example.demo.service.CourseNumberService;
import com.example.demo.service.FacultyService;
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
public class CourseNumberController {

    @Autowired
    private CourseNumberService courseNumberService;


    @GetMapping("/coursenumber")
    public String getStudents(Model model) {
        List<CourseNumberModel> students = courseNumberService.getCourseNumber();
        model.addAttribute("students", students);
//        System.out.println("s : " + students);
        return "coursenumber";
    }

    @PostMapping("/coursenumber/add")
    public String addStudent(@ModelAttribute CourseNumberModel student,
                             BindingResult bindingResult, Model model
    ) {
        courseNumberService.createCourseNumber(student);

        System.out.println("GroupName: " + student);
//        studentService.addStudent(newsStudent);
        return "redirect:/coursenumber";
    }
    @PostMapping("/coursenumber/update")
    public String updateStudent(@ModelAttribute CourseNumberModel student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            List<CourseNumberModel> students = courseNumberService.getCourseNumber();
            model.addAttribute("students", students);
            return "coursenumber";
        }
        courseNumberService.updateCourseNumber(student);
        return "redirect:/coursenumber";
    }
    @PostMapping("/coursenumber/delete")
    public String deleteStudent(@RequestParam Integer id) {
        courseNumberService.deleteCourseNumber(id);
        return "redirect:/coursenumber";
    }
}
