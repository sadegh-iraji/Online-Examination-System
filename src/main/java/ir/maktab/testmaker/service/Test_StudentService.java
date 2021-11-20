package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.model.Test_Student;

import java.util.Optional;

public interface Test_StudentService {

    Test_Student findTest_StudentsById(Long id);

    Optional<Test_Student> findTest_StudentsByTestAndStudent(Test test, Student student);

    Test_Student save(Test_Student test_student);
}
