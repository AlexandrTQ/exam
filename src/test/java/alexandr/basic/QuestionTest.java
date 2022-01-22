package alexandr.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    @Test
    void equalsTrue() {
        Question question1 = new Question("1","2");
        Question question2 = new Question("1","2");
        assertEquals(question1,question2);
    }
    @Test
    void equalsFalse() {
        Question question1 = new Question("3","2");
        Question question2 = new Question("1","2");
        assertNotEquals(question1,question2);
    }
}