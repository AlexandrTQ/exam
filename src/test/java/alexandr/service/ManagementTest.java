package alexandr.service;

import alexandr.basic.Question;
import alexandr.basic.User;
import alexandr.basic.WholeExam;
import alexandr.clientmonitor.ClientMonitor;
import alexandr.dao.DaoExam;
import alexandr.userinput.UserInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayDeque;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ManagementTest {
    @Configuration
    static class managementConfig {
        @MockBean
        private DaoExam daoExam;

        @MockBean
        private ClientMonitor clientMonitor;

        @MockBean
        private UserInput userInput;

        @Bean
        Management management(){
            return new Management(daoExam, userInput, clientMonitor);
        }

    }
    @Autowired
    Management management;

    @Autowired
    DaoExam dao;

    @Autowired
    UserInput userInput;

    @Autowired
    ClientMonitor out;

    @Spy
    WholeExam exam;

    @Test
    void Run() {
        ArrayDeque<Question> questions = new ArrayDeque<>();
        Question question1 = new Question("1","2");
        Question question2 = new Question("3","4");
        questions.add(question1);
        questions.add(question2);
        this.exam.setQuestions(questions);
        when(dao.createExam()).thenReturn(exam);
        when(userInput.getUserInput()).thenReturn("2");
        management.examRun();
        Mockito.verify(dao).createExam();
        Mockito.verify(out).askName();
        Mockito.verify(out,times(2)).printWordQuestion();
        Mockito.verify(exam,times(2)).AskQuestion();
        Mockito.verify(dao).addResult("2",1);
    }
}