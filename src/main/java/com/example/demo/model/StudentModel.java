package com.example.demo.model;

public class StudentModel {
    private int Id;
    private String Name;
    private String Secondname;
    private String GroupName;
    private String Faculty;
    private String Course;
    private boolean Exist;

    public boolean isExist() {
        return Exist;
    }

    public void setExist(boolean exist) {
        Exist = exist;
    }


    public StudentModel(int id, String name, String secondname, String groupName, String faculty, String course, boolean exist) {
        Id = id;
        Name = name;
        Secondname = secondname;
        GroupName = groupName;
        Faculty = faculty;
        Course = course;
        Exist = exist;
    }


    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
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

    public String getSecondname() {
        return Secondname;
    }

    public void setSecondname(String secondname) {
        Secondname = secondname;
    }
}
