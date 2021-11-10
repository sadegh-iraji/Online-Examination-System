package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.MultiChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCQRepository extends JpaRepository<MultiChoiceQuestion, Long> {
}
