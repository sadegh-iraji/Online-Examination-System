package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

        Course findCourseById(Long id);

        @EntityGraph(attributePaths = "students")
        @Query("SELECT c from Course c where c.id=:id")
        Course findCourseByIdWithStudents(@Param("id") Long id);

        @EntityGraph(attributePaths = "tests")
        @Query("SELECT c from Course c where c.id=:id")
        Course findCourseByIdWithTests(@Param("id") Long id);
}
