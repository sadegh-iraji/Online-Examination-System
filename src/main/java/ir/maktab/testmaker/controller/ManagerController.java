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

    // main menu for manager
    @GetMapping("/managerMenu")
    public String managerMenu() {
        return "/manager/managerMenu";
    }

    // show all users to manager
    @GetMapping("/registeredUsers")
    public String registeredUsers(ModelMap modelMap) {
        List<User> registeredUsers = userService.findUsersByUserTypeOrUserType(UserType.TEACHER, UserType.STUDENT);
        modelMap.addAttribute("registeredUsers", registeredUsers);
        return "/manager/registeredUsers";
    }

    // edit information of a registered user
    @PostMapping("/editUser")
    public String editUser(@RequestParam String userId,
                           ModelMap modelMap) {
        User user = userService.findUserById(Long.parseLong(userId));
        modelMap.addAttribute("user", user);
        return "/manager/editUser";
    }

    // confirmation for editing a user
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

    // controller for enter new course data in jsp
    @GetMapping("/newCourse")
    public String newCourse() {
        return "/manager/newCourse";
    }

    // saving new course information
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

    // show all courses
    @GetMapping("/courses")
    public String courses(ModelMap modelMap) {
        List<Course> courses = courseService.findAll();
        modelMap.addAttribute("courses", courses);
        return "/manager/courses";
    }

    // set a teacher for a course that has no teacher
    @PostMapping("/courseTeacherSet")
    public String courseTeacherSet(@RequestParam String id,
                                   ModelMap modelMap) {
        Course course = courseService.findCourseById(Long.parseLong(id));
        /*finding all the teachers that have been activated by the manager*/
        List<Teacher> teachers = teacherService.findTeachersByIsActive(true);
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("teachers", teachers);
        return "/manager/courseTeacherSet";
    }

    // confirmation of setting new teacher for the course
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

    // show all students added to a course
    @PostMapping("/courseStudents")
    public String courseStudents(@RequestParam String id,
                                 ModelMap modelMap) {

        /*finding all the courses by fetching their students information*/
        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(id));
        List<Student> students = course.getStudents();
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", students);
        return "/manager/courseStudents";
    }

    // remove an student from  a course
    @PostMapping("/removeStudentFromCourse")
    @Transactional
    public String removeStudentFromCourse(@RequestParam String studentId,
                                          @RequestParam String courseId) {
        /*finding all the courses by fetching their students information*/
        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(courseId));
        Student student = studentService.findStudentById(Long.parseLong(studentId));
        course.getStudents().remove(student);
        int currentCapacity = course.getCapacity();
        int capacity = currentCapacity + 1;
        course.setCapacity(capacity);
        courseService.save(course);
        return "/manager/removeStudentFromCourse";
    }

    // add new student to a course
    @PostMapping("/courseAddStudent")
    public String courseAddStudent(@RequestParam String id,
                                   ModelMap modelMap) {
        /*finding all the courses by fetching their students information*/
        Course course = courseService.findCourseByIdWithStudents(Long.parseLong(id));
        /*finding all the students that have been activated by the manager*/
        List<Student> students = studentService.findStudentsByIsActive(true);
        List<Student> courseStudents = course.getStudents();
        /*filter students for show only they are not in the course*/
        if (!courseStudents.isEmpty()) students.removeIf(courseStudents::contains);
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", students);
        return "/manager/courseAddStudent";
    }

    // confirmation of adding new student to a course
    @PostMapping("/courseAddStudentConfirm")
    @Transactional
    public String courseAddStudentConfirm(@RequestParam String courseId,
                                          @RequestParam String studentId,
                                          ModelMap modelMap) {
        /*preventing from null exception*/
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
