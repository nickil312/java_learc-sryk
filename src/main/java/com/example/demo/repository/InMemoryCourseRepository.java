package com.example.demo.repository;

import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class InMemoryCourseRepository {

    private final List<CourseModel> COURSES = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public List<CourseModel> getCourcesPage(int page, int size, String year, String faculty, String course, String search,boolean exist) {
        List<CourseModel> students = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            students = COURSES.stream()
                    .filter(s -> s.getName().contains(search) || s.getDescription().contains(search))
                    .collect(Collectors.toList());
        } else {
            students = COURSES;
        }
        System.out.println("Initial list size: " + students.size());


        if (year != null && !year.isEmpty()) {
            students = students.stream()
                    .filter(s -> s.getYear().equals(year))
                    .collect(Collectors.toList());
            System.out.println("After year filter , list size: " + students.size() + year);

        }

        if (faculty != null && !faculty.isEmpty()) {
            students = students.stream()
                    .filter(s -> s.getFaculty().equals(faculty))
                    .collect(Collectors.toList());
            System.out.println("After faculty filter, list size: " + students.size() + faculty);

        }

        if (course != null && !course.isEmpty()) {
            students = students.stream()
                    .filter(s -> s.getCourse().equals(course))
                    .collect(Collectors.toList());
        }
        System.out.println("faculty: 1 " + faculty);
        System.out.println("year: 1 " + year);


        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, students.size());
        return new ArrayList<>(students.subList(startIndex, endIndex));
    }


    public int getTotalPages(int size, String year, String faculty, String course, String search,boolean exist) {
        List<CourseModel> students = getCourcesPage(1, Integer.MAX_VALUE, year, faculty, course, search,exist);
        return (int) Math.ceil((double) students.size() / size);
    }



    public void deleteCourseLogical(List<Integer> studentIds) {
        for (int studentId : studentIds) {
            CourseModel course = getCourseById(studentId);
            if(course == null) {
                return;
            }
            course.setExist(false); // set exist flag to false for logical deletion
        }
    }

    public void deleteCoursePhysical(List<Integer> studentIds) {
        for (int studentId : studentIds) {
            deleteCourse(studentId); // physical deletion
        }
    }
    public CourseModel getCourseById(int id) {
        return COURSES.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    public CourseModel addCourse(CourseModel student) {
        student.setId(idCounter.getAndIncrement());
        COURSES.add(student);
        return student;
    }

    public CourseModel updateCourse(CourseModel student) {
        int StudentIndex = IntStream.range(0,COURSES.size()).filter(index -> COURSES.get(index).getId() == student.getId()).findFirst().orElse(-1);
        return StudentIndex == -1 ? null : COURSES.set(StudentIndex,student);
    }

    public void deleteCourse(int id) {
        var student = getCourseById(id);
        if(student == null) {
            return;
        }
        COURSES.remove(student);

    }





}
