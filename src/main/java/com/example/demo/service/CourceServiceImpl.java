package com.example.demo.service;

import com.example.demo.model.CourseModel;
import com.example.demo.repository.InMemoryCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourceServiceImpl implements CourceService {
    public final InMemoryCourseRepository inMemoryCourseRepository;


    public CourceServiceImpl(InMemoryCourseRepository inMemoryCourseRepository) {
        this.inMemoryCourseRepository = inMemoryCourseRepository;
    }


    @Override
    public List<CourseModel> getCourcesPage(int page, int size, String year, String faculty, String course, String search,boolean exist) {
        return inMemoryCourseRepository.getCourcesPage( page, size,  year,  faculty,  course,  search, exist);

    }

    @Override
    public int getTotalPages(int size, String year, String faculty, String course, String search, boolean exist) {
        return inMemoryCourseRepository.getTotalPages(size,year,faculty,course,search,exist);

    }

    @Override
    public CourseModel getCourseById(int id) {
        return inMemoryCourseRepository.getCourseById(id);
    }

    @Override
    public CourseModel addCourse(CourseModel studentModel) {
        return inMemoryCourseRepository.addCourse(studentModel);
    }

    @Override
    public CourseModel updateCourse(CourseModel studentModel) {
        return inMemoryCourseRepository.updateCourse(studentModel);
    }

    @Override
    public void deleteCourse(int id) {
        inMemoryCourseRepository.deleteCourse(id);
    }

    @Override
    public void deleteCourseLogical(List<Integer> ids) {
        inMemoryCourseRepository.deleteCourseLogical(ids);
    }

    @Override
    public void deleteCoursePhysical(List<Integer> ids) {
        inMemoryCourseRepository.deleteCoursePhysical(ids);
    }
}
