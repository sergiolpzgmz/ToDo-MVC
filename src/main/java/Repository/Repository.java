package Repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listTasks() throws SQLException;
}
