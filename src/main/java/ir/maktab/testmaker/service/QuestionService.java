package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Question;

import java.util.List;

public interface QuestionService {

    void delete(Question question);

    Question findQuestionById(Long id);

    List<Question> findQuestionsByCourse(Course course);

    Question save(Question question);
}
