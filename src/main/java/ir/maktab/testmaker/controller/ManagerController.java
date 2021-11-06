package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Student;
import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.model.User;
import ir.maktab.testmaker.model.enumeration.UserType;
import ir.maktab.testmaker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    final ManagerService managerService;

    final UserService userService;

    final CourseService courseService;

    final TeacherService teacherService;

    final StudentService studentService;

    @GetMapping("/managerMenu")
    public String managerMenu() {
        return "/manager/managerMenu";
    }

    @GetMapping("/registeredUsers")
    public String registeredUsers(ModelMap modelMap) {
        List<User> registeredUsers = userService.findUsersByUserTypeOrUserType(UserType.TEACHER, UserType.STUDENT);
        modelMap.addAttribute("registeredUsers", registeredUsers);
        return "/manager/registeredUsers";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam String userId,
                           ModelMap modelMap) {
        User user = userService.findUserById(Long.parseLong(userId));
        modelMap.addAttribute("user", user);
        return "/manager/editUser";
    }

    @GetMapping("/editUserConfirm")
    @Transactional
    public String editUserConfirm(@RequestParam String id,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname,
                                  @RequestParam String username,
                                  @RequestParam String isActive,
                                  ModelMap modelMap) {
        if (userService.existsUserByUsername(username)){
            modelMap.addAttribute("message", "کاربر با این نام کاربری موجود است");
        } else {
            User user = userService.findUserById(Long.parseLong(id));
            String userFirstname = user.getFirstname();
            String userLastname = user.getLastname();
            String userUsername = user.getUsername();
            Boolean userIsActive = user.getIsActive();
            if (!firstname.isEmpty()) userFirstname = firstname;
            if (!lastname.isEmpty()) userLastname = lastname;
            if (!username.isEmpty()) userUsername = username;
            if (!isActive.isEmpty()) userIsActive = Boolean.parseBoolean(isActive);
            user.setFirstname(userFirstname);
            user.setLastname(userLastname);
            user.setUsername(userUsername);
            user.setIsActive(userIsActive);
            userService.save(user);
        }
        return "/manager/editUserConfirm";
    }

    @GetMapping("/newCourse")
    public String newCourse() {
        return "/manager/newCourse";
    }

    @PostMapping("/courseCreated")
    @Transactional
    public String courseCreated(@RequestParam String subject,
                                @RequestParam String startDate,
                                @RequestParam String finishDate,
                                @RequestParam String capacity) {
        Course course = new Course(subject, LocalDate.parse(startDate), LocalDate.parse(finishDate),
                Integer.parseInt(capacity));
        courseService.save(course);
        return "/manager/courseCreated";
    }

    @GetMapping("/courses")
    public String courses(ModelMap modelMap) {
        List<Course> courses = courseService.findAll();
        modelMap.addAttribute("courses", courses);
        return "/manager/courses";
    }

    @PostMapping("/courseTeacherSet")
    public String courseTeacherSet(@RequestParam String id,
                                   ModelMap modelMap) {
        Course course = courseService.findCourseById(Long.parseLong(id));
        List<Teacher> teachers = teacherService.findTeachersByIsActive(true);
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("teachers", teachers);
        return "/manager/courseTeacherSet";
    }

    @PostMapping("/courseTeacherSetConfirm")
    @Transactional
    public String courseTeacherSetConfirm(@RequestParam String teacherId,
                                          @RequestParam String courseId) {
        Course course = courseService.findCourseById(Long.parseLong(courseId));
        Teacher teacher = teacherService.findTeacherById(Long.parseLong(teacherId));
        course.setTeacher(teacher);
        courseService.save(course);
        return "/manager/courseTeacherSetConfirm";
    }

    @PostMapping("/courseStudents")
    public String courseStudents(@RequestParam String id,
                                 ModelMap modelMap) {

        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(id));
        List<Student> students = course.getStudents();
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", students);
        return "/manager/courseStudents";
    }

    @PostMapping("/removeStudentFromCourse")
    @Transactional
    public String removeStudentFromCourse(@RequestParam String studentId,
                                          @RequestParam String courseId) {
        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(courseId));
        Student student = studentService.findStudentById(Long.parseLong(studentId));
        course.getStudents().remove(student);
        int currentCapacity = course.getCapacity();
        int capacity = currentCapacity + 1;
        course.setCapacity(capacity);
        courseService.save(course);
        return "/manager/removeStudentFromCourse";
    }

    @PostMapping("/courseAddStudent")
    public String courseAddStudent(@RequestParam String id,
                                   ModelMap modelMap) {
        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(id));
        List<Student> students = studentService.findStudentsByIsActive(true);
        List<Student> courseStudents = course.getStudents();
        if (!courseStudents.isEmpty()) students.removeIf(courseStudents::contains);
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", students);
        return "/manager/courseAddStudent";
    }

    @PostMapping("/courseAddStudentConfirm")
    @Transactional
    public String courseAddStudentConfirm(@RequestParam String courseId,
                                          @RequestParam String studentId,
                                          ModelMap modelMap) {
        if (!studentId.isEmpty()) {
            Course course = courseService.findCourseByIdWithStudents(Long.parseLong(courseId));
            Student student = studentService.findStudentById(Long.parseLong(studentId));
            course.getStudents().add(student);
            int currentCapacity = course.getCapacity();
            int capacity = currentCapacity - 1;
            course.setCapacity(capacity);
            courseService.save(course);
        } else {
            modelMap.addAttribute("message", "دانشجویی انتخاب نشد");
        }
        return "manager/courseAddStudentConfirm";
    }
}
