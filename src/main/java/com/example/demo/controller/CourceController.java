package com.example.demo.controller;

import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourceController {
    @Autowired
    private CourceService courceService;

    @GetMapping("/course")
    public String getCource(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String year,
                              @RequestParam(required = false) String faculty,
                              @RequestParam(required = false) String course,
                              @RequestParam(required = false) String search,
                              @RequestParam(defaultValue = "true") boolean exist) {
        System.out.println("year: " + year);
        System.out.println("faculty: " + faculty);

        List<CourseModel> students = courceService.getCourcesPage(page, size, year, faculty, course, search,exist);
        int totalPages = courceService.getTotalPages(size, year, faculty, course, search,exist);
        model.addAttribute("students", students);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("year", year);
        model.addAttribute("faculty", faculty);
        model.addAttribute("course", course);
        model.addAttribute("search", search);
        return "course";
    }
    @PostMapping("/course/add")
    public String addCource(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String year,
                             @RequestParam String faculty,
                             @RequestParam String course,
                             @RequestParam boolean exist) {
        CourseModel newsStudent = new CourseModel(0, name, description,year,faculty,course,exist);
//        newsStudent.setGroupName(groupName);
//        newsStudent.setFaculty(faculty);
//        newsStudent.setCourse(course);
        courceService.addCourse(newsStudent);
        return "redirect:/course";
    }

    @PostMapping("/course/update")
    public String updateStudent(@RequestParam int id,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam String year,
                                @RequestParam String faculty,
                                @RequestParam String course,
                                @RequestParam boolean exist) {

        CourseModel updateStudent = new CourseModel(id, name, description,year,faculty,course,exist);
        courceService.updateCourse(updateStudent);
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
