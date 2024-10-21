package com.example.demo.controller;

import com.example.demo.model.StudentModel;
import com.example.demo.model.StudentProfileModel;
import com.example.demo.service.StudentProfileService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/profiles")
    public String getStudents(Model model) {
        List<StudentProfileModel> profiles = studentProfileService.getStudentsProfiles();
        List<StudentModel> students = studentService.getStudents();
        model.addAttribute("profiles", profiles);
        model.addAttribute("students", students);
        model.addAttribute("profile", new StudentProfileModel()); // Pass a new StudentModel object
        System.out.println("s : " + profiles);

        return "profiles";
    }

    @PostMapping("/profiles/add")
    public String addStudent(@ModelAttribute StudentProfileModel profile,
                             BindingResult bindingResult, Model model
                             ) {
        studentProfileService.createStudentProfile(profile);

        System.out.println("GroupName: " + profile);
//        studentService.addStudent(newsStudent);
        return "redirect:/profiles";
    }
    @PostMapping("/profiles/update")
    public String updateStudent(@ModelAttribute StudentProfileModel student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            List<StudentProfileModel> profiles = studentProfileService.getStudentsProfiles();
            List<StudentModel> students = studentService.getStudents();
            model.addAttribute("profiles", profiles);
            model.addAttribute("students", students);
            model.addAttribute("profile", new StudentProfileModel()); // Pass a new StudentModel object            return "profiles";
        }
        studentProfileService.updateStudentProfile(student);
        return "redirect:/profiles";
    }
    @PostMapping("/profiles/delete")
    public String deleteStudent(@RequestParam Integer id) {
        studentProfileService.deleteStudentProfile(id);
        return "redirect:/profiles";
    }
}
