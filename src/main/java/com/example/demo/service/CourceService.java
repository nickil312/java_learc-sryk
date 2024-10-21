package com.example.demo.service;

import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;

import java.util.List;

public interface CourceService {
    public List<CourseModel> getCourcesAll();
    public List<CourseModel> getCourcesPage(int page, int size, String year, String faculty, String course, String search,boolean exist);
    public int getTotalPages(int size, String year, String faculty, String course, String search,boolean exist);
    public CourseModel getCourseById(int id);
    public List<CourseModel> getCoursesByIds(List<Integer> courseIds);
    public CourseModel addCourse(CourseModel studentModel);
    public CourseModel updateCourse(CourseModel studentModel);
    public void deleteCourse(int id);
    public void deleteCourseLogical(List<Integer> ids);
    public void deleteCoursePhysical(List<Integer> ids);
}
