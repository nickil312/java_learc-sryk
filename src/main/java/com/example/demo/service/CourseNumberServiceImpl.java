package com.example.demo.service;

import com.example.demo.model.CourseNumberModel;
import com.example.demo.repository.CourseNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseNumberServiceImpl implements CourseNumberService {

    private final CourseNumberRepository courseNumberRepository;

    public CourseNumberServiceImpl(CourseNumberRepository courseNumberRepository) {
        this.courseNumberRepository = courseNumberRepository;
    }


    @Override
    public List<CourseNumberModel> getCourseNumber() {
        return courseNumberRepository.findAll();
    }

    @Override
    public CourseNumberModel createCourseNumber(CourseNumberModel student) {
        return courseNumberRepository.save(student);
    }

    @Override
    public CourseNumberModel updateCourseNumber(CourseNumberModel student) {
        return courseNumberRepository.save(student);
    }

    @Override
    public List<CourseNumberModel> getCourseNumbersById(List<Integer> ids) {
        return courseNumberRepository.findAllById(ids);
    }

    @Override
    public CourseNumberModel getCourseNumberById(Integer ids) {
        return courseNumberRepository.findById(ids).orElse(null);
    }

    @Override
    public void deleteCourseNumber(Integer id) {
        courseNumberRepository.deleteById(id);
    }
}
