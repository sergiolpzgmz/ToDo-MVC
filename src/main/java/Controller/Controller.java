package Controller;

import Model.Task;
import Repository.TaskRepositoryImp;
import View.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Controller {
    View view;
    TaskRepositoryImp taskList;
    DefaultTableModel tableModel;

    public Controller(View view) throws SQLException {
        this.view = view;
        taskList = new TaskRepositoryImp();
        tableModel = new DefaultTableModel();

        listTasks(view.tasksTable);
    }

    /*This method runs through tasks list
    (generated previously in TaskRepositoryImp class) and show
    * all tasks in the view */
    private void listTasks(JTable table) throws SQLException {
        tableModel = (DefaultTableModel) table.getModel();
        Object[] object = new Object[4];

        for (Task listTask : taskList.listTasks()) {
            object[0] = listTask.getName();
            object[1] = listTask.getDescription();
            object[2] = listTask.getDeadline();
            object[3] = listTask.getPriority();

            tableModel.addRow(object);
        }
        view.tasksTable.setModel(tableModel);
    }
}
