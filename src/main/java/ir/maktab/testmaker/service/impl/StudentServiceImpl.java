package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.repository.StudentRepository;
import ir.maktab.testmaker.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public List<Student> findStudentsByIsActive(boolean isActive) {
        return studentRepository.findStudentsByIsActive(isActive);
    }

    @Override
    public Student findStudentByUsernameWithCourses(String username) {
        return studentRepository.findStudentByUsernameWithCourses(username);
    }

    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }
}
