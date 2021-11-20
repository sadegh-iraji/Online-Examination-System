package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Answer;
import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Tasq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswersByStudent(Student student);

    Optional<Answer> findAnswerByStudentAndTasq(Student student, Tasq tasq);
}
