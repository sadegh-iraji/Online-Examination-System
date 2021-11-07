package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.Course;
import ir.maktab.testmaker.model.Teacher;
import ir.maktab.testmaker.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

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
        return "/teacher/teacherCourses";
    }
}
