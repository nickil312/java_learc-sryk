package com.example.demo.controller;

import com.example.demo.model.CourseModel;
import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentModel;
import com.example.demo.model.StudentProfileModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.FacultyService;
import com.example.demo.service.StudentProfileService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService; // Service to fetch faculties

    @Autowired
    private CourceService courseService; // Service to fetch courses

    @Autowired
    private StudentProfileService studentProfileService; // Service to fetch courses

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
                              @ModelAttribute StudentModel student) {
        List<StudentModel> students = studentService.getStudents();
        List<FacultyModel> faculties = facultyService.getFaculty(); // Fetch all faculties
        List<CourseModel> courses = courseService.getCourcesAll(); // Fetch all courses
        List<StudentProfileModel> profiles = studentProfileService.getStudentsProfiles(); // Fetch all courses

        model.addAttribute("student", new StudentModel()); // Pass a new StudentModel object
        model.addAttribute("faculties", faculties); // Add faculties to the model
        model.addAttribute("courses", courses); // Add courses to the model
        model.addAttribute("students", students);
        model.addAttribute("profiles", profiles);

        return "students";
    }



    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute StudentModel student,
                             @RequestParam(value = "courses", required = false) List<Integer> courseIds) {

        // Fetch courses based on the selected IDs
        if (courseIds != null) {
            List<CourseModel> selectedCourses = courseService.getCoursesByIds(courseIds);
            student.setCourses(selectedCourses); // Set the selected courses to the student
        }

        // Save the student to the database
        studentService.addStudent(student);

        return "redirect:/students";
    }

    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute StudentModel student,
                                @RequestParam(value = "courses", required = false) List<Integer> courseIds) {

//        StudentModel updateStudent = new StudentModel(id, name, secondname,groupName,faculty,course,exist);
//        studentService.updateStudent(updateStudent);
        if (courseIds != null) {
            List<CourseModel> selectedCourses = courseService.getCoursesByIds(courseIds);
            student.setCourses(selectedCourses); // Set the selected courses to the student
        }

        // Save the student to the database
        studentService.updateStudent(student);

        return "redirect:/students";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
//    @PostMapping("/students/multidelete")
//    public String deleteStudents(@RequestParam("studentIds") List<Integer> studentIds, @RequestParam("deleteType") String deleteType) {
//        if (deleteType.equals("Delete Logical")) {
//            studentService.deleteStudentsLogical(studentIds);
//        } else if (deleteType.equals("Delete Physical")) {
//            studentService.deleteStudentsPhysical(studentIds);
//        }
//        System.out.println("ids" + studentIds);
//        System.out.println("deleteType" + deleteType);
//
//        return "redirect:/students";
//    }

}
