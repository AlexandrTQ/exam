package alexandr.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(PostgresJdbcDao.class)
class PostgresJdbcDaoTest {

    @Autowired
    private PostgresJdbcDao postgresJdbcDao;

    @Test
    void addResult() {
        postgresJdbcDao.addResult("Alex", 2);
        int excepted = 2;
        int result = postgresJdbcDao.getResult("Alex");
        assertEquals(excepted,result);
        assertNotEquals(excepted+1,result);
    }
}