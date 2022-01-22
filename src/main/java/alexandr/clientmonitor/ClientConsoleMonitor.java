package alexandr.clientmonitor;

import alexandr.basic.Question;
import alexandr.basic.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.PrintStream;

@Service
@AllArgsConstructor
public class ClientConsoleMonitor implements ClientMonitor {
    private PrintStream out;

    @Override
    public void print(String s) {
        out.print(s);
    }

    @Override
    public void askName() {
        out.print("Input your name:");
    }

    @Override
    public void printResults(User user, String pass) {
        out.print(user.getName() + ", your score is " + user.getScore()
                + ". Exam is " + pass + ".");
    }

    @Override
    public void printWordQuestion() {
        out.print("Question: ");
    }

    @Override
    public void printQuestionText(Question q) {
        out.print(q.getText());
    }
}
