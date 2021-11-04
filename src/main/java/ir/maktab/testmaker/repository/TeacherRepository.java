package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherById(Long id);

    Teacher findTeacherByIdAndIsActive(Long id, boolean isActive);

    List<Teacher> findTeachersByIsActive(boolean isActive);
}
