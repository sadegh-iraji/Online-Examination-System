package ir.maktab.testmaker.controller;

import ir.maktab.testmaker.model.*;
import ir.maktab.testmaker.model.enumeration.QType;
import ir.maktab.testmaker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    private final Test_StudentService test_studentService;

    private final AnswerService answerService;

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
            List<Tasq> tasqs = tasqService.findTasqsByQuestion(question);
            if (!tasqs.isEmpty()) {
                for (Tasq tasq : tasqs) {
                    tasqService.delete(tasq);
                }
            }
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
                                         ModelMap modelMap) {
        try {
            Tasq tasq = tasqService.findTasqById(Long.parseLong(tasqId));
            tasqService.delete(tasq);
        } catch (Exception e) {
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
        double testScore = 0;
        if (tasqList.isEmpty()) {
            modelMap.addAttribute("message", "سوالی برای این آزمون ثبت نشده است");
        } else {
            for (Tasq tasq : tasqList) {
                testScore += tasq.getScore();
            }
            modelMap.addAttribute("tasqList", tasqList);
            modelMap.addAttribute("testScore", testScore);
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
                                      ModelMap modelMap) {
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
        if (questionsByCourse.isEmpty()) {
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
                                      ModelMap modelMap) {
        try {
            Test test = testService.findTestById(Long.parseLong(testId));
            Question question = questionService.findQuestionById(Long.parseLong(questionId));
            /*adding question to the test*/
            tasqService.save(new Tasq(0, question, test));
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/addQFromBankConfirm";
    }

    @PostMapping("editQuestion")
    public String editQuestion(@RequestParam String questionId,
                               @RequestParam String tasqId,
                               ModelMap modelMap) {
        Question question = questionService.findQuestionById(Long.parseLong(questionId));
        Tasq tasq = tasqService.findTasqById(Long.parseLong(tasqId));
        modelMap.addAttribute("tasq", tasq);
        /*redirect to edit page by question type*/
        switch (question.getQType()) {
            case DQ:
                DescriptiveQuestion descriptiveQuestion = (DescriptiveQuestion) question;
                modelMap.addAttribute("descriptiveQuestion", descriptiveQuestion);
                return "/teacher/dQuestionEdit";
            case MCQ:
                MultiChoiceQuestion multiChoiceQuestion = (MultiChoiceQuestion) question;
                modelMap.addAttribute("mcQuestion", multiChoiceQuestion);
        }
        return "/teacher/mcQuestionEdit";
    }

    /*Method uses for saving changes of both kind of Questions*/
    @PostMapping("editDQuestionConfirm")
    @Transactional
    public String editDQuestionConfirm(@RequestParam String title,
                                       @RequestParam String content,
                                       @RequestParam String id,
                                       @RequestParam String tasqId,
                                       @RequestParam String score,
                                       ModelMap modelMap) {
        Question question = questionService.findQuestionById(Long.parseLong(id));
        Tasq tasq = tasqService.findTasqById(Long.parseLong(tasqId));
        double tasqScore = tasq.getScore();
        String questionTitle = question.getTitle();
        String questionContent = question.getContent();
        /*check which parameters were edit*/
        if (!title.isEmpty()) questionTitle = title;
        if (!content.isEmpty()) questionContent = content;
        if (!score.isEmpty()) tasqScore = Double.parseDouble(score);
        question.setTitle(questionTitle);
        question.setContent(questionContent);
        tasq.setScore(tasqScore);
        try {
            questionService.save(question);
            tasqService.save(tasq);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/editDQuestionConfirm";
    }

    @PostMapping("optionEdit")
    public String optionEdit(@RequestParam String questionId,
                             ModelMap modelMap) {
        MultiChoiceQuestion multiChoiceQuestion = mcqService.findMultiChoiceQuestionById(Long.parseLong(questionId));
        List<Selection> selections = multiChoiceQuestion.getSelections();
        modelMap.addAttribute("mcQuestion", multiChoiceQuestion);
        modelMap.addAttribute("options", selections);
        return "teacher/optionEdit";
    }

    @PostMapping("editOptionConfirm")
    @Transactional
    public String editMCQuestionConfirm(@RequestParam String content,
                                        @RequestParam String id,
                                        ModelMap modelMap) {
        Selection selection = selectionService.findSelectionById(Long.parseLong(id));
        String selectionContent = selection.getContent();
        if (!content.isEmpty()) selectionContent = content;
        try {
            selection.setContent(selectionContent);
            selectionService.save(selection);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "مشکلی پیش آمده است");
        }
        return "/teacher/editOptionConfirm";
    }

    @PostMapping("testResults")
    public String testResults(@RequestParam String testId,
                              ModelMap modelMap) {
        Test test = testService.findTestById(Long.parseLong(testId));
        List<Test_Student> test_studentsByTest = test_studentService.findTest_StudentsByTest(test);
        // check if test is in progress
        test_studentsByTest.removeIf(test_student -> test_student.getFinishTime().isAfter(LocalDateTime.now()));
        // check if there is students took the test
        modelMap.addAttribute("message", "تا کنون دانشجویی در آزمون شرکت نکرده است");
        modelMap.addAttribute("test_students", test_studentsByTest);
        modelMap.addAttribute("test", test);
        return "/teacher/testResults";
    }

    @PostMapping("editResult")
    public String editResult(@RequestParam String test_studentId,
                             ModelMap modelMap) {
        Test_Student test_student = test_studentService.findTest_StudentByIdWithAnswers(Long.parseLong(test_studentId));
        List<Answer> answers = test_student.getAnswers();
        modelMap.addAttribute("test_student", test_student);
        modelMap.addAttribute("answers", answers);
        modelMap.addAttribute("DQ", QType.DQ);
        return "/teacher/editResult";
    }

    @PostMapping("editAnswerScore")
    public @ResponseBody
    double editAnswerScore(@RequestParam String answerId,
                           @RequestParam String answerScore,
                           @RequestParam String test_studentId,
                           ModelMap modelMap) {
        Test_Student test_student = test_studentService.findTest_StudentByIdWithAnswers(Long.parseLong(test_studentId));
        Answer answerById = answerService.findAnswerById(Long.parseLong(answerId));
        double score = Double.parseDouble(answerScore);
        // check if score is not bigger than question score and not minus
        if (score > answerById.getTasq().getScore()) {
            score = answerById.getTasq().getScore();
        } else if (score < 0) {
            score = 0;
        }
        // update score of the question
        answerById.setScore(score);
        answerService.save(answerById);
        // update student's total score
        double totalScore = 0;
        for (Answer answer : test_student.getAnswers()) {
            totalScore += answer.getScore();
        }
        test_student.setTotalScore(totalScore);
        test_studentService.save(test_student);
        modelMap.addAttribute("test_student", test_student);
        modelMap.addAttribute("answers", test_student.getAnswers());
        modelMap.addAttribute("DQ", QType.DQ);
        return totalScore;
    }
}
