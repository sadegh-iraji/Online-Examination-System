package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Selection;
import ir.maktab.testmaker.repository.SelectionRepository;
import ir.maktab.testmaker.service.SelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectionServiceImpl implements SelectionService {

    private final SelectionRepository selectionRepository;


    @Override
    public Selection save(Selection selection) {
        return selectionRepository.save(selection);
    }
}
