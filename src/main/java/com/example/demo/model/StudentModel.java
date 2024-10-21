package com.example.demo.model;

//public class StudentModel {
//    private int Id;
//    private String Name;
//    private String Secondname;
//    private String GroupName;
//    private String Faculty;
//    private String Course;
//    private boolean Exist;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "students")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 10, message = "Name must not exceed 10 characters")
    private String name;

    @NotBlank(message = "secondName is required")
    @Size(max = 10, message = "secondName must not exceed 10 characters")
    private String secondName;

    private String groupName;
    private boolean exist;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyModel faculty;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfileModel profile;

//    @OneToMany(mappedBy = "student")
//    private List<EnrollmentModel> enrollments;
    @ManyToMany
    @JoinTable (name="student_course",
            joinColumns=@JoinColumn (name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id"))
    private List<CourseModel> courses;

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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public FacultyModel getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyModel faculty) {
        this.faculty = faculty;
    }

    public StudentProfileModel getProfile() {
        return profile;
    }

    public void setProfile(StudentProfileModel profile) {
        this.profile = profile;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}
