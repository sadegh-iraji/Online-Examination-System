package ir.maktab.testmaker.repository;

import ir.maktab.testmaker.model.User;
import ir.maktab.testmaker.model.enumeration.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<User> findUserByUsername(String username);

    User findUserById(Long id);

    List<User> findUsersByUserTypeOrUserType(UserType userType1, UserType userType2);



}
