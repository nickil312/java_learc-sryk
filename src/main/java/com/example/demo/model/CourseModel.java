package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;
    @NotBlank(message = "Year is required")
    @Size(max = 4, message = "Year must not exceed 100 characters")
    private String year;

    private boolean exist;

    @ManyToOne
    @JoinColumn(name = "course_number_id")
    private CourseNumberModel courseNumber;

    @ManyToMany
    @JoinTable (name="student_course",
            joinColumns=@JoinColumn (name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id"))
    private List<StudentModel> students;

    @OneToMany(mappedBy = "course")
    private List<ScheduleModel> schedules;


    public List<ScheduleModel> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleModel> schedules) {
        this.schedules = schedules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public CourseNumberModel getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(CourseNumberModel courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }
}
