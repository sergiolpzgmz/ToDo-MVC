package Controller;

import javax.swing.*;
import java.sql.SQLException;

public interface ControllerActions {
    public void listTasksAction(JTable table) throws SQLException;
    public void insertNewTaskAction();
    public void updateTaskAction();
    public void deleteTaskAction();
}
