package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findStudentById(Long id);

    List<Student> findStudentsByIsActive(boolean isActive);

    Student findStudentByUsernameWithCourses(String username);

    Student findStudentByUsername(String username);
}
