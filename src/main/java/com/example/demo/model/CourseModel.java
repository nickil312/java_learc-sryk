package com.example.demo.model;

public class CourseModel {
    private int Id;
    private String Name;
    private String Description;
    private String Faculty;
    private String Year;
    private String Course;
    private boolean Exist;

    public CourseModel(int id, String name, String description, String faculty, String year, String course, boolean exist) {
        Id = id;
        Name = name;
        Description = description;
        Faculty = faculty;
        Year = year;
        Course = course;
        Exist = exist;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public boolean isExist() {
        return Exist;
    }

    public void setExist(boolean exist) {
        Exist = exist;
    }
}
