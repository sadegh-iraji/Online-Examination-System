package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Course;

import java.util.List;

public interface CourseService {

    Course findCourseById(Long id);

    Course findCourseByIdWithStudents(Long id);

    Course save(Course course);

    List<Course> findAll();
}
