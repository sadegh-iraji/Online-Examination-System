package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Question;
import ir.maktab.testmaker.repository.QuestionRepository;
import ir.maktab.testmaker.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public List<Question> findQuestionsByCourse(Course course) {
        return questionRepository.findQuestionsByCourse(course);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }
}
