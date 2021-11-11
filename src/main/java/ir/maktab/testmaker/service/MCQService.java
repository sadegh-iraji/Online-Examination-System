package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.MultiChoiceQuestion;
import org.springframework.data.repository.query.Param;

public interface MCQService {

    MultiChoiceQuestion save(MultiChoiceQuestion multiChoiceQuestion);

    MultiChoiceQuestion findMultiChoiceQuestionById(Long id);

//    MultiChoiceQuestion findMCQByIdWithSelections(Long id);
}
