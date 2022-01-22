package alexandr.service;

import alexandr.basic.Question;
import alexandr.basic.User;
import alexandr.basic.WholeExam;
import alexandr.clientmonitor.ClientMonitor;
import alexandr.dao.DaoExam;
import alexandr.userinput.UserInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class Management {

    public Management(@Qualifier("postgresJdbcDao") DaoExam daoExam,
                      @Qualifier("consoleInput") UserInput input,
                      @Qualifier("clientConsoleMonitor")ClientMonitor output) {
        this.daoExam = daoExam;
        this.input = input;
        this.output = output;
    }

    private final DaoExam daoExam;
    private final UserInput input;
    private final ClientMonitor output;

    public void examRun() {
        WholeExam exam;
        try {
            exam = daoExam.createExam();
        } catch (IllegalArgumentException e) {
            output.print(e.getMessage());
            return;
        }
        int passingScore = (int) (exam.getQuestions().size() * 0.6);

        output.askName();
        User user = new User(input.getUserInput());

        while (!exam.getQuestions().isEmpty()) {
            if (ask(exam, user)) {
                user.AddScore();
            }
        }
        output.printResults(user, checkResult(user, passingScore));
        try {
            daoExam.addResult(user.getName(), user.getScore());
        } catch (DuplicateKeyException e) {
            output.print("result didn't recorded");
        }
    }

    private boolean ask(WholeExam exam, User user) {
        Question currentQuestion = exam.AskQuestion();
        output.printWordQuestion();
        output.printQuestionText(currentQuestion);
        return input.getUserInput().equals(currentQuestion.getCorrectAnswer());
    }

    private String checkResult(User user, int passingScore) {
        if (user.getScore() >= passingScore) {
            return "pass";
        }
        return "fail";
    }
}
