package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.repository.CourseRepository;
import ir.maktab.testmaker.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public Course findCourseByIdWithStudents(Long id) {
        return courseRepository.findCourseByIdWithStudents(id);
    }

    @Override
    public Course findCourseByIdWithTests(Long id) {
        return courseRepository.findCourseByIdWithTests(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @EntityGraph(attributePaths = "teacher")
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
