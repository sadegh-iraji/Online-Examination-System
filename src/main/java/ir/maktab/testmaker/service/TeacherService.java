package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherById(Long id);

    Teacher findTeacherByIdAndIsActive(Long id, boolean isActive);

    Teacher findTeacherByUsername(String username);

    Teacher findTeacherByUsernameWithCourses(String username);

    List<Teacher> findTeachersByIsActive(boolean isActive);

    List<Teacher> findAll();
}
