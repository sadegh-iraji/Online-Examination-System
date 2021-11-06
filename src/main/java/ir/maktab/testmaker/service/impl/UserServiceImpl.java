package ir.maktab.testmaker.service.impl;

import ir.maktab.testmaker.model.Manager;
import ir.maktab.testmaker.model.User;
import ir.maktab.testmaker.model.enumeration.UserType;
import ir.maktab.testmaker.repository.UserRepository;
import ir.maktab.testmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    @Transactional
    public void initUsers() {
        if (userRepository.count() == 0) {
            Manager manager = new Manager("محمدصادق", "ایرجی", "manager", "manager", UserType.MANAGER, true);
            userRepository.save(manager);
        }
    }

    @Override
    public List<User> findUsersByUserTypeOrUserType(UserType userType1, UserType userType2) {
        return userRepository.findUsersByUserTypeOrUserType(userType1,userType2);
    }

    @Override
    public Boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
