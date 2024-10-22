package com.example.demo.service;

import com.example.demo.model.CourseModel;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.InMemoryCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourceServiceImpl implements CourceService {
//    public final InMemoryCourseRepository inMemoryCourseRepository;
//
//
//    public CourceServiceImpl(InMemoryCourseRepository inMemoryCourseRepository) {
//        this.inMemoryCourseRepository = inMemoryCourseRepository;
//    }
    private final CourseRepository courseRepository;

    public CourceServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



    @Override
    public List<CourseModel> getCourcesAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseModel> getCourcesPage(int page, int size, String year, String faculty, String course, String search,boolean exist) {
        return null;

    }

    @Override
    public int getTotalPages(int size, String year, String faculty, String course, String search, boolean exist) {
        return 0;

    }

    @Override
    public CourseModel getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<CourseModel> getCoursesByIds(List<Integer> courseIds) {
        return courseRepository.findAllById(courseIds);
    }

    @Override
    public CourseModel addCourse(CourseModel studentModel) {
        return courseRepository.save(studentModel);
    }

    @Override
    public CourseModel updateCourse(CourseModel studentModel) {
        return courseRepository.save(studentModel);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteCourseLogical(List<Integer> ids) {
//        inMemoryCourseRepository.deleteCourseLogical(ids);
    }

    @Override
    public void deleteCoursePhysical(List<Integer> ids) {
//        inMemoryCourseRepository.deleteCoursePhysical(ids);
    }
}
