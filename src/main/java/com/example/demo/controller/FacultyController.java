package com.example.demo.controller;

import com.example.demo.model.FacultyModel;
import com.example.demo.model.StudentProfileModel;
import com.example.demo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    //    @GetMapping("/faculty")
//    public Mono<String> getFaculty(Model model) {
//        WebClient webClient = WebClient.create("http://localhost:8080");
//        return webClient.get()
//                .uri("/v1/api/faculty")
//                .retrieve()
//                .bodyToMono(String.class)
//                .doOnNext(response -> model.addAttribute("students", response))
//                .then(Mono.just("faculty")); // Возвращаем "faculty" как результат
//    }
    @GetMapping("/faculty")
    public String getStudents(Model model) {

        List<FacultyModel> students = facultyService.getFaculty();
        model.addAttribute("students", students);
//        System.out.println("s : " + students);
        return "faculty";
    }

    @PostMapping("/faculty/add")
    public String addStudent(@ModelAttribute FacultyModel student,
                             BindingResult bindingResult, Model model
    ) {
        facultyService.createFaculty(student);

        System.out.println("GroupName: " + student);
//        studentService.addStudent(newsStudent);
        return "redirect:/faculty";
    }

    @PostMapping("/faculty/update")
    public String updateStudent(@ModelAttribute FacultyModel student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            List<FacultyModel> students = facultyService.getFaculty();
            model.addAttribute("students", students);
            return "faculty";
        }
        facultyService.updateFaculty(student);
        return "redirect:/faculty";
    }

    @PostMapping("/faculty/delete")
    public String deleteStudent(@RequestParam Integer id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }
}
