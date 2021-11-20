package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long id);

    List<Student> findStudentsByIsActive(boolean isActive);

    Student findStudentByUsername(String username);

    @EntityGraph(attributePaths = "courses")
    @Query("SELECT s from Student s where s.username=:username")
    Student findStudentByUsernameWithCourses(@Param("username") String username);
}
