package Repository;

import java.sql.SQLException;
import java.util.List;

public interface AllTasksRepository<T> {
    public List<T> listTasks();
    public void insertNewTask(T t);
    public void updateTask(T t, int id);
    public void deleteTask(int id);

}
