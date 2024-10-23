package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "schedules")
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Day of the week is required")
    @Size(max = 20, message = "Day of the week must not exceed 20 characters")
    private String dayOfWeek;

    @NotBlank(message = "Pair number is required")
    @Size(max = 15, message = "Pair number must not exceed 15 characters")
    private String pairNumber;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Day of the week is required") @Size(max = 20, message = "Day of the week must not exceed 20 characters") String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(@NotBlank(message = "Day of the week is required") @Size(max = 20, message = "Day of the week must not exceed 20 characters") String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public @NotBlank(message = "Pair number is required") @Size(max = 15, message = "Pair number must not exceed 15 characters") String getPairNumber() {
        return pairNumber;
    }

    public void setPairNumber(@NotBlank(message = "Pair number is required") @Size(max = 15, message = "Pair number must not exceed 15 characters") String pairNumber) {
        this.pairNumber = pairNumber;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}


