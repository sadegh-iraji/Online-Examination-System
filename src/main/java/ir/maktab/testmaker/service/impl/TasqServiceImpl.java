package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.repository.TasqRepository;
import ir.maktab.testmaker.service.TasqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasqServiceImpl implements TasqService {

    private final TasqRepository tasqRepository;

    @Override
    public List<Tasq> findTasqsByTest(Test test) {
        return tasqRepository.findTasqsByTest(test);
    }

    @Override
    public Tasq save(Tasq tasq) {
        return tasqRepository.save(tasq);
    }
}
