package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.model.Test_Student;
import ir.maktab.testmaker.repository.Test_StudentRepository;
import ir.maktab.testmaker.service.Test_StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Test_StudentServiceImpl implements Test_StudentService {

    private final Test_StudentRepository test_studentRepository;

    @Override
    public Test_Student findTest_StudentsById(Long id) {
        return test_studentRepository.findTest_StudentsById(id);
    }

    @Override
    public Optional<Test_Student> findTest_StudentsByTestAndStudent(Test test, Student student) {
        return test_studentRepository.findTest_StudentsByTestAndStudent(test, student);
    }

    @Override
    @Transactional
    public Test_Student save(Test_Student test_student) {
        return test_studentRepository.save(test_student);
    }

    @Override
    public Test_Student findTest_StudentByIdWithAnswers(Long id) {
        return test_studentRepository.findTest_StudentByIdWithAnswers(id);
    }

    @Override
    public List<Test_Student> findTest_StudentsByTest(Test test) {
        return test_studentRepository.findTest_StudentsByTest(test);
    }
}
