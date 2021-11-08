package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Test;

public interface TestService {

    void delete(Test test);

    Test findTestById(Long id);
}
