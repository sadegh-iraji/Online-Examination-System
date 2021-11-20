package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Test;
import org.springframework.data.repository.query.Param;

public interface TestService {

    void delete(Test test);

    Test findTestById(Long id);

    Test findTestByIdWithStudents(Long id);
}
