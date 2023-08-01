package Controller;

import javax.swing.*;
import java.sql.SQLException;

public interface ControllerActions {
    void listTasksAction(JTable table) throws SQLException;
    void insertNewTaskAction();
    void updateTaskAction();
    void deleteTaskAction();
}
