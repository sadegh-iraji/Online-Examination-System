package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.model.Test_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Test_StudentRepository extends JpaRepository<Test_Student, Long> {

    Test_Student findTest_StudentsById(Long id);

    Optional<Test_Student> findTest_StudentsByTestAndStudent(Test test, Student student);
}
