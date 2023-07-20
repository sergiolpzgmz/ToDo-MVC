package Repository;

import java.sql.SQLException;
import java.util.List;

public interface AllTasksRepository<T> {
    public List<T> listTasks() throws SQLException;
    public void insertNewTask(T t);
}
