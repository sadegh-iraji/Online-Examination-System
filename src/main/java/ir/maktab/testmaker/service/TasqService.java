package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Question;
import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TasqService {

    Tasq findTasqById(Long id);

    void delete(Tasq tasq);

    List<Tasq> findTasqsByTest(Test test);

    Tasq save(Tasq tasq);

    List<Tasq> findTasqsByQuestion(Question question);

    Page<Tasq> findAllPageable(Pageable pageable);
}
