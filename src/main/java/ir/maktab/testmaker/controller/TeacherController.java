package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.model.Test;
import ir.maktab.testmaker.service.CourseService;
import ir.maktab.testmaker.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
}
