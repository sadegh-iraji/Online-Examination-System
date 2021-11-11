package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.MultiChoiceQuestion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MCQRepository extends JpaRepository<MultiChoiceQuestion, Long> {


    MultiChoiceQuestion findMultiChoiceQuestionById(Long id);

//    @EntityGraph(attributePaths = "selections")
//    @Query("SELECT s from Selection s where s.id=:id")
//    MultiChoiceQuestion findMCQByIdWithSelections(@Param("id")Long id);
}
