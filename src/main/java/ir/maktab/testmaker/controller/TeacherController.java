package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.*;
import ir.maktab.testmaker.model.enumeration.QType;
import ir.maktab.testmaker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final CourseService courseService;

    private final TestService testService;

    private final TasqService tasqService;

    private final DescriptiveQuestionService descriptiveQuestionService;

    private final QuestionService questionService;

    private final SelectionService selectionService;

    private final MCQService mcqService;

    // Main Menu of Teacher
    @GetMapping("/teacherMenu")
    public String teacherMenu() {
        return "/teacher/teacherMenu";
    }

    // Show Courses Teaching by the Teacher
    @GetMapping("/teacherCourses")
    public String teacherCourses(ModelMap modelMap) {
        String activeUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Teacher teacher = teacherService.findTeacherByUsernameWithCourses(activeUsername);
        List<Course> teacherCourses = teacher.getCourses();
        modelMap.addAttribute("teacherCourses", teacherCourses);
        modelMap.addAttribute("teacher", teacher);
        return "/teacher/teacherCourses";
    }

    // List of Tests made for a course
    @PostMapping("courseTests")
    public String courseTests(@RequestParam String courseId,
                              @RequestParam String teacherId,
                              ModelMap modelMap) {
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
                             ModelMap modelMap) {
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
                              ModelMap modelMap) {
        try {
            Course course = courseService.findCourseByIdWithTests(Long.parseLong(courseId));
            Test test = new Test(subject, description, Integer.parseInt(time), course);
            course.getTests().add(test);
            courseService.save(course);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }

        return "/teacher/testCreated";
    }

    @PostMapping("deleteTest")
    @Transactional
    public String deleteTest(@RequestParam String testId,
                             ModelMap modelMap) {
        try {
            Test test = testService.findTestById(Long.parseLong(testId));
            testService.delete(test);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/deleteTest";
    }

    @PostMapping("deleteQuestion")
    @Transactional
    public String deleteQuestion(@RequestParam String questionId,
                                 ModelMap modelMap) {
        try {
            Question question = questionService.findQuestionById(Long.parseLong(questionId));
            questionService.delete(question);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/deleteQuestion";
    }

    @PostMapping("deleteQuestionFromTest")
    @Transactional
    public String deleteQuestionFromTest(@RequestParam String tasqId,
                                         ModelMap modelMap){
        try {
        Tasq tasq = tasqService.findTasqById(Long.parseLong(tasqId));
        tasqService.delete(tasq);
        } catch (Exception e){
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/deleteQuestionFromTest";
    }

    @PostMapping("editTest")
    public String editTest(@RequestParam String testId,
                           ModelMap modelMap) {
        Test test = testService.findTestById(Long.parseLong(testId));
        List<Tasq> tasqList = tasqService.findTasqsByTest(test);
        modelMap.addAttribute("test", test);
        if (tasqList.isEmpty()) {
            modelMap.addAttribute("message", "سوالی برای این آزمون ثبت نشده است");
        } else {
            modelMap.addAttribute("tasqList", tasqList);
        }
        return "/teacher/editTest";
    }

    @PostMapping("addNewQuestion")
    public String addNewQuestion(@RequestParam String testId,
                                 ModelMap modelMap) {
        Test test = testService.findTestById(Long.parseLong(testId));
        modelMap.addAttribute("test", test);
        return "/teacher/addNewQuestion";
    }

    @PostMapping("addNewDQ")
    public String addNewDQ(@RequestParam String testId,
                           ModelMap modelMap) {
        Test test = testService.findTestById(Long.parseLong(testId));
        modelMap.addAttribute("test", test);
        return "/teacher/addNewDQ";
    }

    @PostMapping("addNewDQConfirm")
    @Transactional
    public String addNewDQConfirm(@RequestParam String title,
                                  @RequestParam String content,
                                  @RequestParam String testId,
                                  @RequestParam String score,
                                  ModelMap modelMap) {
        try {
            Test test = testService.findTestById(Long.parseLong(testId));
            Course course = test.getCourse();
            Teacher teacher = course.getTeacher();
            double qScore = Double.parseDouble(score);
            // Add new question to database and to course and teacher bank
            DescriptiveQuestion descriptiveQuestion = new DescriptiveQuestion(title, content, QType.DQ
                    , course, teacher);
            descriptiveQuestionService.save(descriptiveQuestion);
            // Map Question, Test and Score of question in the test
            Tasq tasq = new Tasq(qScore, descriptiveQuestion, test);
            tasqService.save(tasq);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/addNewDQConfirm";
    }

    @PostMapping("addNewMSQ")
    public String addNewMSQ(@RequestParam String testId,
                            ModelMap modelMap) {
        modelMap.addAttribute("testId", testId);
        return "/teacher/addNewMSQ";
    }

    @PostMapping("addNewMSQConfirm")
    @Transactional
    public String addNewMSQConfirm(@RequestParam(value = "options[]") String[] options,
                                   @RequestParam String title,
                                   @RequestParam String content,
                                   @RequestParam String testId,
                                   @RequestParam String score,
                                   @RequestParam String answer,
                                   ModelMap modelMap) {
        Test test = testService.findTestById(Long.parseLong(testId));
        Course course = test.getCourse();
        Teacher teacher = course.getTeacher();
        boolean isAnswer = false;
        try {
            // Add new question to database and to course and teacher bank
            MultiChoiceQuestion multiChoiceQuestion = new MultiChoiceQuestion(title, content,
                    QType.MCQ, course, teacher);
            mcqService.save(multiChoiceQuestion);
            // Add Every Options to database and map to question
            for (String option : options) {
                // Check if option is equals answer ignoring white spaces ......................................
                isAnswer = option.replaceAll("\\s+", "")
                        .equalsIgnoreCase(answer.replaceAll("\\s+", ""));
                selectionService.save(new Selection(option, isAnswer, multiChoiceQuestion));
            }
            // Map Question, Test and Score of question in the test
            tasqService.save(new Tasq(Double.parseDouble(score), multiChoiceQuestion, test));

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/addNewMSQConfirm";
    }

    // show question bank of the course to choose question from
    @PostMapping("addQuestionFromBank")
    public String addQuestionFromBank(@RequestParam String testId,
                                      ModelMap modelMap){
        Test test = testService.findTestById(Long.parseLong(testId));
        Course course = test.getCourse();
        /*find question bank of the course*/
        List<Question> questionsByCourse = questionService.findQuestionsByCourse(course);
        /*check if the test contains the question*/
        List<Tasq> tasqsByTest = tasqService.findTasqsByTest(test);
        List<Question> questionsByTest = new ArrayList<>();
        for (Tasq tasq : tasqsByTest) {
            questionsByTest.add(tasq.getQuestion());
        }
        questionsByCourse.removeIf(questionsByTest::contains);
        if (questionsByCourse.isEmpty()){
            modelMap.addAttribute("message", "سوالی متفاوت از سوالات استفاده شده در آزمون از بانک سوالات یافت نشد");
        } else {
            modelMap.addAttribute("questions", questionsByCourse);
            modelMap.addAttribute("course", course);
            modelMap.addAttribute("test", test);
        }
        return "/teacher/addQuestionFromBank";
    }

    @PostMapping("addQFromBankConfirm")
    @Transactional
    public String addQFromBankConfirm(@RequestParam String testId,
                                      @RequestParam String questionId,
                                      ModelMap modelMap){
        try {
        Test test = testService.findTestById(Long.parseLong(testId));
        Question question = questionService.findQuestionById(Long.parseLong(questionId));
        /*adding question to the test*/
        tasqService.save(new Tasq(0, question,test));
        } catch (Exception e){
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/addQFromBankConfirm";
    }
}
