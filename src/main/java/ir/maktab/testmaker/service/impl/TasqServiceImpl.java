package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Question;
import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.repository.TasqRepository;
import ir.maktab.testmaker.service.TasqService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasqServiceImpl implements TasqService {

    private final TasqRepository tasqRepository;

    @Override
    public Tasq findTasqById(Long id) {
        return tasqRepository.findTasqById(id);
    }

    @Override
    public void delete(Tasq tasq) {
        tasqRepository.delete(tasq);
    }

    @Override
    public List<Tasq> findTasqsByTest(Test test) {
        return tasqRepository.findTasqsByTest(test);
    }

    @Override
    public Tasq save(Tasq tasq) {
        return tasqRepository.save(tasq);
    }

    @Override
    public List<Tasq> findTasqsByQuestion(Question question) {
        return tasqRepository.findTasqsByQuestion(question);
    }

    @Override
    public Page<Tasq> findAllPageable(Pageable pageable) {
        return tasqRepository.findAll(pageable);
    }
}
