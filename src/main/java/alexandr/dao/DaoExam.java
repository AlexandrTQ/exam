package alexandr.dao;

import alexandr.basic.WholeExam;

public interface DaoExam {
    WholeExam createExam();
    void addResult(String name, int result);
}
