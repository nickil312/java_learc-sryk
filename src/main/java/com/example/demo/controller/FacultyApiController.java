package com.example.demo.controller;

import com.example.demo.model.FacultyModel;
import com.example.demo.service.FacultyService;
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
    public FacultyModel updateFaculty(@PathVariable Integer id, @RequestBody FacultyModel facultyModel) {
        facultyModel.setId(id);
        return facultyService.updateFaculty(facultyModel);
    }

    @PostMapping
    public FacultyModel createFaculty(@RequestBody FacultyModel faculty) {
//        System.out.println(faculty);
        return facultyService.createFaculty(faculty);
    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
    }

}
