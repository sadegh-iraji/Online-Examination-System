package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.repository.TeacherRepository;
import ir.maktab.testmaker.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    final TeacherRepository teacherRepository;

    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findTeacherById(id);
    }

    @Override
    public Teacher findTeacherByIdAndIsActive(Long id, boolean isActive) {
        return teacherRepository.findTeacherByIdAndIsActive(id, isActive);
    }

    @Override
    public Teacher findTeacherByUsername(String username) {
        return teacherRepository.findTeacherByUsername(username);
    }

    @Override
    public Teacher findTeacherByUsernameWithCourses(String username) {
        return teacherRepository.findTeacherByUsernameWithCourses(username);
    }

    @Override
    public List<Teacher> findTeachersByIsActive(boolean isActive) {
        return teacherRepository.findTeachersByIsActive(isActive);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
