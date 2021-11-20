package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Answer;
import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Tasq;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Answer save(Answer answer);

    List<Answer> findAnswersByStudent(Student student);

    Optional<Answer> findAnswerByStudentAndTasq(Student student, Tasq tasq);
}
