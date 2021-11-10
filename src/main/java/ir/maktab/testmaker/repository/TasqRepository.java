package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Tasq;
import ir.maktab.testmaker.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasqRepository extends JpaRepository<Tasq, Long> {

    Tasq findTasqById(Long id);

    List<Tasq> findTasqsByTest(Test test);
}
