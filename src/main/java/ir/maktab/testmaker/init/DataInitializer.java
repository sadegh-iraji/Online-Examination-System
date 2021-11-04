package ir.maktab.testmaker.init;

import ir.maktab.testmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserService userService;

    @PostConstruct
    public void initData() {
        initUsers();
    }

    private void initUsers() {
        userService.initUsers();
    }

}
