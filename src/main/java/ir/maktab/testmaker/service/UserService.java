package ir.maktab.testmaker.service;

import ir.maktab.testmaker.model.User;
import ir.maktab.testmaker.model.enumeration.UserType;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findUserByUsername(String username);

    User findUserById(Long id);

    void initUsers();

    List<User> findUsersByUserTypeOrUserType(UserType userType1, UserType userType2);

    Boolean existsUserByUsername(String username);

}
