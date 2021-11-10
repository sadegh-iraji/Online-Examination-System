package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionById(Long id);

    List<Question> findQuestionsByCourse(Course course);
}
