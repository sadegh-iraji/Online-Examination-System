package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherById(Long id);

    Teacher findTeacherByIdAndIsActive(Long id, boolean isActive);

    Teacher findTeacherByUsername(String username);

    @EntityGraph(attributePaths = "courses")
    @Query("select t from Teacher t where t.username=:username")
    Teacher findTeacherByUsernameWithCourses(@Param("username") String username);

    List<Teacher> findTeachersByIsActive(boolean isActive);
}
