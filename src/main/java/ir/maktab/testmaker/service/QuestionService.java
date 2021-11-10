package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Question;

public interface QuestionService {

    void delete(Question question);

    Question findQuestionById(Long id);
}
