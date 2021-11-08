package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.service.CourseService;
import ir.maktab.testmaker.service.TeacherService;
import ir.maktab.testmaker.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final TestService testService;

    @GetMapping("/teacherMenu")
    public String teacherMenu(){
        return "/teacher/teacherMenu";
    }

    @GetMapping("/teacherCourses")
    public String teacherCourses(ModelMap modelMap){
        String activeUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherService.findTeacherByUsernameWithCourses(activeUsername);
        List<Course> teacherCourses = teacher.getCourses();
        modelMap.addAttribute("teacherCourses", teacherCourses);
        modelMap.addAttribute("teacher", teacher);
        return "/teacher/teacherCourses";
    }

    @PostMapping("courseTests")
    public  String courseTests(@RequestParam String courseId,
                               @RequestParam String teacherId,
                               ModelMap modelMap){
        Course course = courseService.findCourseByIdWithTests(Long.parseLong(courseId));
        List<Test> courseTests = course.getTests();
        Teacher teacher = teacherService.findTeacherById(Long.parseLong(teacherId));
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("courseTests", courseTests);
        modelMap.addAttribute("teacher", teacher);
        return "/teacher/courseTests";
    }

    @PostMapping("addNewTest")
    public String addNewTest(@RequestParam String courseId,
                             ModelMap modelMap){
        Course course = courseService.findCourseById(Long.parseLong(courseId));
        modelMap.addAttribute("course", course);
        return "/teacher/addNewTest";
    }

    @PostMapping("testCreated")
    @Transactional
    public String testCreated(@RequestParam String courseId,
                              @RequestParam String subject,
                              @RequestParam String description,
                              @RequestParam String time,
                              ModelMap modelMap){
        try {
            Course course = courseService.findCourseByIdWithTests(Long.parseLong(courseId));
            Test test = new Test(subject, description, Integer.parseInt(time));
            course.getTests().add(test);
            courseService.save(course);
        } catch (Exception e){
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }

        return "/teacher/testCreated";
    }

    @PostMapping("deleteTest")
    @Transactional
    public String deleteTest(@RequestParam String testId,
                             ModelMap modelMap){
        try {
            Test test = testService.findTestById(Long.parseLong(testId));
            testService.delete(test);
        } catch (Exception e){
            e.printStackTrace();
            modelMap.addAttribute("message","مشکلی پیش آمده است");
        }

        return "/teacher/deleteTest";
    }
}
