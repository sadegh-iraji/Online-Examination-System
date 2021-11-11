package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.Selection;

public interface SelectionService {

    Selection save(Selection selection);

    Selection findSelectionById(Long id);
}
