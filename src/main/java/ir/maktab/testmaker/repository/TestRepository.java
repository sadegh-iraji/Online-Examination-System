package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    Test findTestById(Long id);
}
