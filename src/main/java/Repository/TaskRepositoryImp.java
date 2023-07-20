package Repository;

import Model.Task;
import Util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImp implements AllTasksRepository<Task> {

    /**
     * Create connection
     *
     * @return the instance of the connection obtained
     * in the DataBaseConnection class after invoking
     * the getConnectionInstance() method
     */
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    /**
     * Launches a database query to list the tasks.
     * @return a new array with the list of tasks that contains the database
     */
    @Override
    public List<Task> listTasks() {
        List<Task>tasks = new ArrayList<>();

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

    /**
     * insert into database the new task
     *
     * @param task task to be added
     */
    @Override
    public void insertNewTask(Task task) {
        String sqlInsert = "INSERT INTO task(name, description, deadline, priority) VALUES (?,?,?,?)";

        try(PreparedStatement stmt = getConnection().prepareStatement(sqlInsert)){
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new Date(task.getDeadline().getTime()));
            stmt.setString(4, task.getPriority());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
