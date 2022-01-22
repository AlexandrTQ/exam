package alexandr.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholeExam {
    private Queue<Question> questions;

    public Queue<Question> getQuestions() {
        return questions;
    }
    public Question AskQuestion() {
        if (!questions.isEmpty()) {
            return questions.poll();
        }
        return new Question("empty", "empty");
    }
}
