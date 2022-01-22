package alexandr.basic;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class WholeExamTest {

    @Test
    void equalsTrue() {
        Queue<Question> questions = new ArrayDeque<>();
        Question question1 = new Question("1","2");
        Question question2 = new Question("3","4");
        questions.add(question1);
        questions.add(question2);
        WholeExam wholeExam1 = new WholeExam(questions);
        WholeExam wholeExam2 = new WholeExam(questions);
        assertEquals(wholeExam1, wholeExam2);
    }
    @Test
    void equalsFalse() {
        Queue<Question> questions = new ArrayDeque<>();
        Question question1 = new Question("1","2");
        Question question2 = new Question("3","4");
        questions.add(question1);
        questions.add(question2);
        WholeExam wholeExam1 = new WholeExam(questions);
        WholeExam wholeExam2 = new WholeExam(null);
        assertNotEquals(wholeExam1, wholeExam2);
    }
    @Test
    void getQuestion() {
        Queue<Question> questions = new ArrayDeque<>();
        Question question1 = new Question("1","2");
        Question question2 = new Question("3","4");
        questions.add(question1);
        questions.add(question2);
        WholeExam wholeExam = new WholeExam(questions);
        Question excepted = new Question("1","2");
        assertEquals(excepted,wholeExam.AskQuestion());
    }
    @Test
    void getEmptyQuestion() {
        WholeExam wholeExam = new WholeExam(new ArrayDeque<>());
        Question excepted = new Question("empty","empty");
        assertEquals(excepted,wholeExam.AskQuestion());
    }
}