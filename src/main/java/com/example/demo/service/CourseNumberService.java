package com.example.demo.service;

import com.example.demo.model.CourseNumberModel;
import com.example.demo.model.FacultyModel;

import java.util.List;

public interface CourseNumberService {
    public List<CourseNumberModel> getCourseNumber();
    public CourseNumberModel createCourseNumber(CourseNumberModel student);
    public CourseNumberModel updateCourseNumber(CourseNumberModel student);
    public List<CourseNumberModel> getCourseNumbersById(List<Integer> ids);
    public CourseNumberModel getCourseNumberById(Integer ids);
    public void deleteCourseNumber(Integer id);
}
