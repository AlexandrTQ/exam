package alexandr.clientmonitor;

import alexandr.basic.Question;
import alexandr.basic.User;

public interface ClientMonitor {
    void print(String s);
    void askName();
    void printResults(User user, String pass);
    void printWordQuestion();
    void printQuestionText(Question q);
}
