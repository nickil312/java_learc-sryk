package com.example.demo.model;

public class CourseNumberModel {
    private int Id;
    private String Name;
    private boolean Exist;

    public CourseNumberModel(int id, String name, boolean exist) {
        Id = id;
        Name = name;
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

    public boolean isExist() {
        return Exist;
    }

    public void setExist(boolean exist) {
        Exist = exist;
    }
}
