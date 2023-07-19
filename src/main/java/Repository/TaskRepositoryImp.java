package Repository;

import Model.Task;
import Util.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImp implements Repository<Task> {

    /*Returns the instance of the connection obtained
    * in the DataBaseConnection class after invoking
    * the getConnectionInstance() method*/
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    //Returns a new array with the list of tasks that contains the database
    @Override
    public List<Task> listTasks() throws SQLException {
        List<Task>tasks = new ArrayList<Task>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM task")) {

            while (rs.next()){
                Task task = new Task();
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));
                task.setPriority(rs.getString("priority"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}
