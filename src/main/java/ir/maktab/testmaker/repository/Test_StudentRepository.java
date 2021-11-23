package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.model.Test_Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Test_StudentRepository extends JpaRepository<Test_Student, Long> {

    Test_Student findTest_StudentsById(Long id);

    Optional<Test_Student> findTest_StudentsByTestAndStudent(Test test, Student student);

    @EntityGraph(attributePaths = "answers")
    @Query("SELECT ts FROM Test_Student ts where ts.id=:id")
    Test_Student findTest_StudentByIdWithAnswers(@Param("id") Long id);

    List<Test_Student> findTest_StudentsByTest(Test test);

    List<Test_Student> findTest_StudentsByStudent(Student student);
}
