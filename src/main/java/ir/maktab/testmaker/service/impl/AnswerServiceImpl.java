package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Answer;
import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.repository.AnswerRepository;
import ir.maktab.testmaker.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    @Transactional
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> findAnswersByStudent(Student student) {
        return answerRepository.findAnswersByStudent(student);
    }

    @Override
    public Optional<Answer> findAnswerByStudentAndTasq(Student student, Tasq tasq) {
        return answerRepository.findAnswerByStudentAndTasq(student, tasq);
    }

    @Override
    public Answer findAnswerById(Long id) {
        return answerRepository.findAnswerById(id);
    }

}
