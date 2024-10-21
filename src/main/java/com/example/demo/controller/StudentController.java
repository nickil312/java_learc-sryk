package com.example.demo.controller;

import com.example.demo.model.StudentModel;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    //    @GetMapping("/students")
//    public String getStudents(Model model) {
//        model.addAttribute("students",studentService.getStudents());
//        return "students";
//    }
//    @GetMapping("/students")
//    public String getStudents(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
//        List<StudentModel> students = studentService.getStudentsPage(page, size);
//        int totalPages = studentService.getTotalPages(size);
//        model.addAttribute("students", students);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("currentPage", page);
//        return "students";
//    }
    @GetMapping("/students")
    public String getStudents(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String groupName,
                              @RequestParam(required = false) String faculty,
                              @RequestParam(required = false) String course,
                              @RequestParam(required = false) String search,
                              @RequestParam(defaultValue = "true") boolean exist) {
        List<StudentModel> students = studentService.getStudentsPage(page, size, groupName, faculty, course, search,exist);
        int totalPages = studentService.getTotalPages(size, groupName, faculty, course, search,exist);
        model.addAttribute("students", students);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("groupName", groupName);
        model.addAttribute("faculty", faculty);
        model.addAttribute("course", course);
        model.addAttribute("search", search);
        return "students";
    }



    @PostMapping("/students/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String secondname,
                             @RequestParam String groupName,
                             @RequestParam String faculty,
                             @RequestParam String course,
                             @RequestParam boolean exist) {
        StudentModel newsStudent = new StudentModel(0, name, secondname,groupName,faculty,course,exist);
//        newsStudent.setGroupName(groupName);
//        newsStudent.setFaculty(faculty);
//        newsStudent.setCourse(course);
//        System.out.println("GroupName: " + groupName);
        studentService.addStudent(newsStudent);
        return "redirect:/students";
    }

    @PostMapping("/students/update")
    public String updateStudent(@RequestParam int id,
                                @RequestParam String name,
                                @RequestParam String secondname,
                                @RequestParam String groupName,
                                @RequestParam String faculty,
                                @RequestParam String course,
                                @RequestParam boolean exist) {

        StudentModel updateStudent = new StudentModel(id, name, secondname,groupName,faculty,course,exist);
        studentService.updateStudent(updateStudent);
        return "redirect:/students";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @PostMapping("/students/multidelete")
    public String deleteStudents(@RequestParam("studentIds") List<Integer> studentIds, @RequestParam("deleteType") String deleteType) {
        if (deleteType.equals("Delete Logical")) {
            studentService.deleteStudentsLogical(studentIds);
        } else if (deleteType.equals("Delete Physical")) {
            studentService.deleteStudentsPhysical(studentIds);
        }
        System.out.println("ids" + studentIds);
        System.out.println("deleteType" + deleteType);

        return "redirect:/students";
    }

}
