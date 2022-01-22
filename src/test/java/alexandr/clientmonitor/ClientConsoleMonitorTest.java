package alexandr.clientmonitor;
import alexandr.basic.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClientConsoleMonitorTest {
    @Mock
    PrintStream out;

    @Test
    void printResults() {
        ClientMonitor clientMonitor = new ClientConsoleMonitor(out);
        User user = new User("Alex");
        user.AddScore();
        clientMonitor.printResults(user, "fail");
        Mockito.verify(out).print("Alex, your score is 1. Exam is fail.");
        Mockito.verify(out, times(0)).print("asfgggd");
    }

    @Test
    void printResultsFail() {
        ClientMonitor clientMonitor = new ClientConsoleMonitor(out);
        User user = new User("Alex");
        user.AddScore();
        clientMonitor.printResults(user, "fail");
        Mockito.verify(out, times(0)).print("asfgggd");
    }
}