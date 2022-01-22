package alexandr.dao;

import alexandr.basic.Question;
import alexandr.basic.WholeExam;
import alexandr.configs.PropsConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@AllArgsConstructor
public class CsvDao implements DaoExam {
    private final PropsConfig propsConfig;
    private final QuestionMapper questionMapper = new QuestionMapper();

    @Override
    public WholeExam createExam() {
        IllegalArgumentException e = new IllegalArgumentException("problems with resource file");
        try(InputStream in = CsvDao.class.getResourceAsStream(propsConfig.getCsvFilepath())) {
            if(Objects.isNull(in)) throw e;
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8);
            String csvString = scanner.useDelimiter("\\A").next().replaceAll("[\u0000-\u001f]", "");
            return questionMapper.createExam(csvString);
        } catch (IOException a) {
            throw e;
        }
    }
    @Override

    public void addResult(String name, int result) {}

    private static class QuestionMapper {
        private WholeExam createExam(String input) {
            List<String> text = separateText(input);
            Queue<Question> questions = new ArrayDeque<>();
            for (String o : text) {
                String questionText = o.split(";")[0];
                String answer = o.split(": ")[1];
                questions.add(new Question(questionText, answer));
            }
            return new WholeExam(questions);
        }

        private List<String> separateText(String text) {
            String[] t = text.split("\\d[)] ");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < t.length; i++) {
                if (!t[i].isEmpty()) {
                    list.add(t[i]);
                }
            }
            return list;
        }
    }
}
