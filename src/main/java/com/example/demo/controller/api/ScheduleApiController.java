package com.example.demo.controller.api;

import com.example.demo.model.FacultyModel;
import com.example.demo.model.ScheduleModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/schedule")
public class ScheduleApiController {
    private final ScheduleService scheduleService;

    private final CourceService courceService;

    public ScheduleApiController(ScheduleService scheduleService, CourceService courceService) {
        this.scheduleService = scheduleService;
        this.courceService = courceService;
    }
    @GetMapping
    public List<ScheduleModel> scheduleModelList(){
        return scheduleService.getSchedule();
    }

    @GetMapping("/{id}")
    public ScheduleModel facultyById(@PathVariable Integer id) {
        return scheduleService.getScheduleById(id);
    }

    @PostMapping
    public ScheduleModel createFaculty(@Valid @RequestBody ScheduleModel schedule, BindingResult bindingResult) {
//        System.out.println(faculty);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }
        return scheduleService.createSchedule(schedule);
    }
    @PatchMapping("/{id}")
    public ScheduleModel updateFaculty(@Valid @PathVariable Integer id,
                                      @RequestBody ScheduleModel schedule,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // Return to the form view with error messages
//            fack 3
            return null; // Ensure this returns the correct view name
        }

        schedule.setId(id);
        return scheduleService.updateSchedule(schedule);
    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
    }
}
