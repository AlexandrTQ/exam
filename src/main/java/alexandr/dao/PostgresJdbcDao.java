package alexandr.dao;

import alexandr.basic.Question;
import alexandr.basic.WholeExam;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostgresJdbcDao implements DaoExam {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public WholeExam createExam() {
        List<Question> questions = jdbc.query("Select * from questions", new QuestionMapper());
        return new WholeExam(new ArrayDeque<>(questions));
    }

    @Override
    public void addResult(String name, int result) {
            jdbc.getJdbcOperations().update("insert into results values (?,?,?)", count()+1, name, result);
    }

    public int getResult(String name) {
        return jdbc.getJdbcOperations().queryForObject("select score from results where name ='"+name+"';", Integer.class);
    }

    private int count() {
        return jdbc.getJdbcOperations().queryForObject("Select count(*) from results;", Integer.class);
    }

    private static class QuestionMapper implements RowMapper<Question> {

        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            String text = rs.getString("question");
            String answer = rs.getString("answer");
            return new Question(text,answer);
        }
    }
}
