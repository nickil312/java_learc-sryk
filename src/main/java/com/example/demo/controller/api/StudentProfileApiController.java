package com.example.demo.controller.api;

import com.example.demo.model.StudentModel;
import com.example.demo.model.StudentProfileModel;
import com.example.demo.service.StudentProfileService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/profiles")
public class StudentProfileApiController {

    private final StudentProfileService studentProfileService;
    private final StudentService studentService;

    public StudentProfileApiController(StudentProfileService studentProfileService, StudentService studentService) {
        this.studentProfileService = studentProfileService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentProfileModel> studentProfileModelList() {
        return studentProfileService.getStudentsProfiles();
    }

    @GetMapping("/{id}")
    public StudentProfileModel studentProfileModel(@PathVariable int id) {
        return studentProfileService.getStudentProfileById(id);
    }

    @PostMapping
    public StudentProfileModel studentProfileModel(@Valid @RequestBody StudentProfileModel studentProfileModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        return studentProfileService.createStudentProfile(studentProfileModel);
    }

    @PatchMapping("/{id}")
    public StudentProfileModel updatestudentProfileModel(@Valid @PathVariable Integer id,
                                                         @RequestBody StudentProfileModel studentProfileModel,
                                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return null; // Возвращаем null или обрабатываем ошибку по-другому
        }

// Получаем существующий профиль
        StudentProfileModel existingProfile = studentProfileService.getStudentProfileById(id);
//        StudentProfileModel oldProfile = studentProfileService.getStudentProfileById(studentProfileModel.getStudent().getId());
        if (existingProfile == null) {
            // Обработка случая, когда профиль не найден
            return null; // Или выбросьте исключение
        }
//        System.out.println("search done");
//        System.out.println("existingProfile" + existingProfile.getId() + existingProfile.getStudent());
//        System.out.println("oldProfile" + oldProfile.getId() + oldProfile.getStudent());
////        System.out.println("search done");
//        oldProfile.setStudent(null);
//
//        existingProfile.setStudent(studentProfileModel.getStudent());
//        existingProfile.setAddress(studentProfileModel.getAddress());
//        existingProfile.setBirthDate(studentProfileModel.getBirthDate());
//        System.out.println("update old done");
//
//        studentProfileService.updateStudentProfile(oldProfile);
        System.out.println("update old done" + studentProfileModel.getStudent());
        studentProfileModel.setId(id);
        return studentProfileService.updateStudentProfile(studentProfileModel);
    }
    @DeleteMapping("/{id}")
    public void deleteStudentProfileModel(@PathVariable int id) {
        studentProfileService.deleteStudentProfile(id);
    }
}
