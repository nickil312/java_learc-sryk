package com.example.demo.controller.api;

import com.example.demo.model.FacultyModel;
import com.example.demo.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/faculty")
public class FacultyApiController {
    private final FacultyService facultyService;

    public FacultyApiController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping
    public List<FacultyModel> facultyList() {
        return facultyService.getFaculty();
    }

    @GetMapping("/{id}")
    public FacultyModel facultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    @PatchMapping("/{id}")
    public FacultyModel updateFaculty(@Valid @PathVariable Integer id,
                                      @RequestBody FacultyModel facultyModel,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }

        facultyModel.setId(id);
        return facultyService.updateFaculty(facultyModel);
    }

    @PostMapping
    public FacultyModel createFaculty(@Valid @RequestBody FacultyModel faculty, BindingResult bindingResult) {
//        System.out.println(faculty);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        return facultyService.createFaculty(faculty);
    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
    }

}
