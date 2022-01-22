package alexandr.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addScoreSomeTimes() {
        User user = new User("Alex");
        user.AddScore();
        user.AddScore();
        user.AddScore();
        int expected = 3;
        assertEquals(expected,user.getScore());
    }

    @Test
    void addScoreZero() {
        User user = new User("Alex");
        int expected = 0;
        assertEquals(expected,user.getScore());
    }

    @Test
    void GetName() {
        User user = new User("Alex");
        String excepted = "Alex";
        assertEquals(excepted,"Alex");
    }
}