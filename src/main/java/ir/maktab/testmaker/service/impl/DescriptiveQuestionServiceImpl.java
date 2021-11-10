package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.DescriptiveQuestion;
import ir.maktab.testmaker.repository.DescriptiveQuestionRepository;
import ir.maktab.testmaker.service.DescriptiveQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptiveQuestionServiceImpl implements DescriptiveQuestionService {

    private final DescriptiveQuestionRepository descriptiveQuestionRepository;

    @Override
    public DescriptiveQuestion save(DescriptiveQuestion descriptiveQuestion) {
        return descriptiveQuestionRepository.save(descriptiveQuestion);
    }
}
