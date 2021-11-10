package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.DescriptiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiveQuestionRepository extends JpaRepository<DescriptiveQuestion, Long> {
}
