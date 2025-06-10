package pro.skyjava.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;
import java.util.Set;

@RestController
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/exam/java/add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/exam/java/remove")
    public Question removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }

    @GetMapping("/exam/java")
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/exam/java/find")
    public Set<String> findQuestionBySearchTerm(@RequestParam("searchTerm") String searchTerm) {
        return javaQuestionService.findQuestionBySearchTerm(searchTerm);
    }
}
