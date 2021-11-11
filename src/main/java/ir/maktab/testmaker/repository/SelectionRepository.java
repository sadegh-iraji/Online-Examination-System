package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Long> {

    Selection findSelectionById(Long id);
}
