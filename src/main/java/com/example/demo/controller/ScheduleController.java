package com.example.demo.controller;

import com.example.demo.model.CourseModel;
import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.ScheduleModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourceService;
import com.example.demo.service.ScheduleService;
import jakarta.validation.Valid;
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
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourceService courceService;

    @GetMapping("/schedule")
    public String getScheduleAll(Model model) {
        List<CourseModel> courses = courceService.getCourcesAll();
        List<ScheduleModel> schedules = scheduleService.getSchedule();


        model.addAttribute("courses", courses); // Use a different name for the list of courses
        model.addAttribute("schedules", schedules); // Add students to the model
        model.addAttribute("schedule", new ScheduleModel()); // Pass a new CourseModel object

        System.out.println("schedule data: " + schedules);
        return "schedule";
    }

    @PostMapping("/schedule/add")
    public String addSchedule(@Valid @ModelAttribute ScheduleModel schedule, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            // Return to the form view with error messages
            return "schedule"; // Ensure this returns the correct view name
        }
        System.out.println(schedule.getPairNumber());
        System.out.println(schedule.getCourse());
        System.out.println(schedule.getDayOfWeek());
        scheduleService.createSchedule(schedule);
        return "redirect:/schedule";
    }

    @PostMapping("/schedule/update")
    public String updateSchedule(@Valid @ModelAttribute ScheduleModel schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());

            // Return to the form view with error messages
            return "schedule"; // Ensure this returns the correct view name
        }
        System.out.println(schedule.getPairNumber());
        System.out.println(schedule.getCourse());
        System.out.println(schedule.getDayOfWeek());

        scheduleService.updateSchedule(schedule);
        return "redirect:/schedule";
    }

    @PostMapping("/schedule/delete")
    public String deleteSchedule(@RequestParam int id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule";
    }
}
