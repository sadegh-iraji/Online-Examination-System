package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.repository.TestRepository;
import ir.maktab.testmaker.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {

    protected final TestRepository testRepository;

    @Override
    public void delete(Test test) {
        testRepository.delete(test);
    }

    @Override
    public Test findTestById(Long id) {
        return testRepository.findTestById(id);
    }

    @Override
    public Test findTestByIdWithStudents(Long id) {
        return testRepository.findTestByIdWithStudents(id);
    }
}
