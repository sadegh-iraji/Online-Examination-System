package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Test;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    Test findTestById(Long id);

    @EntityGraph(attributePaths = "students")
    @Query("SELECT t FROM Test t where t.id=:id")
    Test findTestByIdWithStudents(@Param("id") Long id);
}
