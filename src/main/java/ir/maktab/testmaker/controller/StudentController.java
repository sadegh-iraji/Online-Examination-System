package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.*;
import ir.maktab.testmaker.model.enumeration.QType;
import ir.maktab.testmaker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    private final CourseService courseService;

    private final TestService testService;

    private final TasqService tasqService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final Test_StudentService test_studentService;

    @GetMapping("studentMenu")
    public String studentMenu() {
        return "/student/studentMenu";
    }

    @GetMapping("studentCourses")
    public String studentCourses(ModelMap modelMap) {
        String activeUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.findStudentByUsernameWithCourses(activeUsername);
        List<Course> courses = student.getCourses();
        if (courses.isEmpty()) {
            modelMap.addAttribute("message", "شما هنوز توسط مدیر به دوره ای اضافه نشده اید");
        } else {
            modelMap.addAttribute("student", student);
            modelMap.addAttribute("courses", courses);
        }
        return "/student/studentCourses";
    }

    @PostMapping("courseTests")
    public String courseTests(@RequestParam String courseId,
                              @RequestParam String studentId,
                              ModelMap modelMap) {
        Course course = courseService.findCourseByIdWithTests(Long.parseLong(courseId));
        List<Test> courseTests = course.getTests();
        Student student = studentService.findStudentById(Long.parseLong(studentId));
        /*check if student had this test before and test time is passed*/

        for (int i = 0; i < courseTests.size(); i++) {
            Optional<Test_Student> optionalTest_student = test_studentService.findTest_StudentsByTestAndStudent(courseTests.get(i), student);
            if (optionalTest_student.isPresent() && (optionalTest_student.get().getFinishTime().isBefore(LocalDateTime.now()))) {
                courseTests.remove(courseTests.get(i));
                i -= 1;
            }
        }
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("courseTests", courseTests);
        modelMap.addAttribute("student", student);
        return "/student/courseTests";
    }

    @RequestMapping(value = {"startTest", "startTest/{page}"})
    public String startTest(@RequestParam(required = false) String testId,
                            @RequestParam(required = false) String tasqId,
                            @RequestParam(required = false) String answer,
                            ModelMap modelMap,
                            @PathVariable(required = false, name = "page") String page,
                            HttpServletRequest request, HttpServletResponse response) {
        Student student = studentService.findStudentByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Tasq> tasqsByTest = new ArrayList<>();
        Test_Student test_student = new Test_Student();
        List<Answer> answers = answerService.findAnswersByStudent(student);

        if (testId != null) {
            Test test = testService.findTestById(Long.parseLong(testId));
            tasqsByTest = tasqService.findTasqsByTest(test);
            Optional<Test_Student> optionalTest_student = test_studentService.findTest_StudentsByTestAndStudent(test, student);
            if (optionalTest_student.isEmpty()) {
                test_student = new Test_Student(LocalDateTime.now().plusMinutes((long) test.getTime()), student, test);
                test_studentService.save(test_student);
            } else {
                test_student = optionalTest_student.get();
            }
            modelMap.addAttribute("test", test);
            modelMap.addAttribute("tasqs", tasqsByTest);
            modelMap.addAttribute("totalCount", tasqsByTest.size());
            request.getSession().setAttribute("studentId", student.getId());
            request.getSession().setAttribute("testTime", test.getTime());
            request.getSession().setAttribute("testId", test.getId());
            request.getSession().setAttribute("test_student", test_student);
        }

        if (request.getSession().getAttribute("testId") != null) {
            request.getSession().setAttribute("test_student", request.getSession().getAttribute("test_student"));
            request.getSession().setAttribute("testId", request.getSession().getAttribute("testId"));
            request.getSession().setAttribute("testTime", request.getSession().getAttribute("testTime"));
        }

        // saving answer
        if (tasqId != null && answer != null) {
            // check if answer is not empty
            if (!answer.isEmpty()) {
                Tasq answeredTasq = tasqService.findTasqById(Long.parseLong(tasqId));
                Optional<Answer> optionAnswer = answerService.findAnswerByStudentAndTasq(student, answeredTasq);
                // check if this question already answered
                if (optionAnswer.isPresent()) {
                    Answer tasqAnswer = optionAnswer.get();
                    // check if question is multi choice question
                    if (answeredTasq.getQuestion().getQType().equals(QType.MCQ)) {
                        MultiChoiceQuestion mcQuestion = (MultiChoiceQuestion) answeredTasq.getQuestion();
                        Selection selection = mcQuestion.getSelections().stream().filter(Selection::isAnswer).collect(Collectors.toList()).get(0);
                        // check if answer is the correct answer
                        if (selection.getContent().equals(answer)) {
                            tasqAnswer.setScore(answeredTasq.getScore());
                        } else tasqAnswer.setScore(0);
                    }
                    tasqAnswer.setContent(answer);
                    answerService.save(tasqAnswer);
                } else {
                    double score = 0;
                    // check if question is multi choice question
                    if (answeredTasq.getQuestion().getQType().equals(QType.MCQ)) {
                        MultiChoiceQuestion mcQuestion = (MultiChoiceQuestion) answeredTasq.getQuestion();
                        Selection selection = mcQuestion.getSelections().stream().filter(Selection::isAnswer).collect(Collectors.toList()).get(0);
                        // check if answer is the correct answer
                        if (selection.getContent().equals(answer)) {
                            score = answeredTasq.getScore();
                        }
                    }
                    answerService.save(new Answer(answer, score, student, answeredTasq, test_student));
                }
            }
        }

        PagedListHolder<Tasq> tasqList;
        if (page == null) {
            tasqList = new PagedListHolder<Tasq>();

            // Setting the source for PagedListHolder
            tasqList.setSource(tasqsByTest);
            tasqList.setPageSize(1);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("tasqList", tasqList);
        } else if (page.equals("prev")) {
            // get the user list from session
            tasqList = (PagedListHolder<Tasq>) request.getSession().getAttribute("tasqList");
            // switch to previous page
            tasqList.previousPage();
        } else if (page.equals("next")) {
            tasqList = (PagedListHolder<Tasq>) request.getSession().getAttribute("tasqList");
            // switch to next page
            tasqList.nextPage();
        } else {
            int pageNum = Integer.parseInt(page);
            tasqList = (PagedListHolder<Tasq>) request.getSession().getAttribute("tasqList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            tasqList.setPage(pageNum - 1);
        }
        modelMap.addAttribute("student", student);
        modelMap.addAttribute("answers", answers);
        modelMap.addAttribute("tasqList", tasqList);
        modelMap.addAttribute("DQ", QType.DQ);
        return "/student/startTest";
    }

    @RequestMapping("finishTest")
    public String finishTest(@RequestParam(required = false) String test_studentId) {
        if (test_studentId != null) {
            Test_Student test_student = test_studentService.findTest_StudentsById(Long.parseLong(test_studentId));
            test_student.setFinishTime(LocalDateTime.now());
            test_studentService.save(test_student);
        }
        return "/student/finishTest";
    }

    @GetMapping("testResults")
    public String testResults(ModelMap modelMap) {
        Student student = studentService.findStudentByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Test_Student> test_studentsByStudent = test_studentService.findTest_StudentsByStudent(student);
        if (test_studentsByStudent.isEmpty()) {
            modelMap.addAttribute("message", "شما در آزمونی شرکت نکرده اید");
        } else {
            test_studentsByStudent.removeIf(test_student -> test_student.getFinishTime().isAfter(LocalDateTime.now()));
            modelMap.addAttribute("test_student", test_studentsByStudent);
        }
        return "/student/testResults";
    }
}
