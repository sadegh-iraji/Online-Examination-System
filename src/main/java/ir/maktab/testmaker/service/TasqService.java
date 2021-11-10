package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.model.Test;

import java.util.List;

public interface TasqService {

    List<Tasq> findTasqsByTest(Test test);

    Tasq save(Tasq tasq);
}
