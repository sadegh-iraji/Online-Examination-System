package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.model.User;
import ir.maktab.testmaker.model.enumeration.UserType;
import ir.maktab.testmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    //implementing login page -----------------------------------------------------------------
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
      /*  if (error != null) {
            errorMessge = "نام کابری یا کلمه عبور اشتباه است";
        }*/
        if (logout != null) {
            errorMessge = "شما با موفقیت از حساب کاربری خود خارج شدید";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    //implementing logout page -----------------------------------------------------------------
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

    //make register page -----------------------------------------------------------------
    @GetMapping("/logup")
    public String logUp(ModelMap modelMap) {
        modelMap.addAttribute("teacher", UserType.TEACHER);
        modelMap.addAttribute("student", UserType.STUDENT);
        return "logup";
    }

    @PostMapping("/logupResult")
    @Transactional
    public String logupResult(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String firstname,
                              @RequestParam String lastname,
                              @RequestParam String userType,
                              ModelMap modelMap) {
        if (userService.existsUserByUsername(username)){
            modelMap.addAttribute("message", "کاربر با این نام کاربری موجود است");
        } else {
            UserType registerUserType = UserType.valueOf(userType);
            if (registerUserType.equals(UserType.TEACHER)) {
                Teacher teacher = new Teacher(firstname, lastname, username, password, UserType.TEACHER, false);
                userService.save(teacher);
            } else {
                Student student = new Student(firstname, lastname, username, password, UserType.STUDENT, false);
                userService.save(student);
            }
        }
        return "logupResult";
    }

    //implementing access denied page -----------------------------------------------------------------
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    //home page for redirection on user menu -----------------------------------------------------------------
    @RequestMapping("/home")
    public String home() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.findUserByUsername(username);
        switch (user.get().getUserType()) {
            case MANAGER:
                return "manager/managerMenu";
            case STUDENT:
                return "student/studentMenu";
            case TEACHER:
                return "teacher/teacherMenu";
        }
        return "";
    }
}
