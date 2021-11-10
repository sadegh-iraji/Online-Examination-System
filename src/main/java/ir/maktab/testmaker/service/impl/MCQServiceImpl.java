package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.MultiChoiceQuestion;
import ir.maktab.testmaker.repository.MCQRepository;
import ir.maktab.testmaker.service.MCQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;


    @Override
    public MultiChoiceQuestion save(MultiChoiceQuestion multiChoiceQuestion) {
        return mcqRepository.save(multiChoiceQuestion);
    }
}
