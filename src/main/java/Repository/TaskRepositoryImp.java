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
     *
     * @return a new array with the list of tasks that contains the database
     */
    @Override
    public List<Task> listTasks() {
        List<Task>tasks = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM task")) {

            while (rs.next()){
                Task task = new Task();

                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));
                task.setPriority(rs.getString("priority"));
                task.setFinished(rs.getBoolean("finished"));

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
        String sqlInsert = "INSERT INTO task(name, description, deadline, priority, finished) VALUES (?,?,?,?,?)";

        try(PreparedStatement stmt = getConnection().prepareStatement(sqlInsert)){
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());

            if(task.getDeadline() == null){
                // Insert empty date
                stmt.setNull(3, Types.DATE);
            }
            else{ stmt.setDate(3, new Date(task.getDeadline().getTime())); }

            stmt.setString(4, task.getPriority());
            stmt.setBoolean(5, task.isFinished());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * update selected task
     *
     * @param task task to be updated
     */
    @Override
    public void updateTask(Task task, int id) {
        String sqlUpdate = "UPDATE task set name=?, description=?, deadline=?, priority=?, finished=? WHERE id=?";

        try(PreparedStatement stmt = getConnection().prepareStatement(sqlUpdate)){
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new Date(task.getDeadline().getTime()));
            stmt.setString(4, task.getPriority());
            stmt.setBoolean(5, task.isFinished());
            stmt.setInt(6,id);

            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * removes selected tasks based on the id
     *
     * @param id id of the task to be deleted*/
    @Override
    public void deleteTask(int id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM task WHERE id=?")){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
