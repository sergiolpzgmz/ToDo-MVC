package Controller;

import Model.Task;
import Repository.TaskRepositoryImp;
import View.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class Controller implements ActionListener {
    View view;
    TaskRepositoryImp taskListDB;
    DefaultTableModel tableModel;

    public Controller(View view) throws SQLException {
        this.view = view;
        taskListDB = new TaskRepositoryImp();
        tableModel = new DefaultTableModel();

        this.view.btnAdd.addActionListener(this);
        this.view.btnCancel.addActionListener(this);

        listTasks(view.tasksTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addBtnAction(e);
        cancelBtnAction(e);
    }
    private void addBtnAction(ActionEvent e) {
        if(e.getSource()==view.btnAdd) {
            insertNewTask();

            try {
                listTasks(view.tasksTable);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void cancelBtnAction(ActionEvent e){
        if(e.getSource()==view.btnCancel) clearForm();
    }

    /**
    * Runs through tasks list (generated previously in TaskRepositoryImp class) and show
    * all tasks in the view
    *
    * @param table the Jtable element
    */
    private void listTasks(JTable table) throws SQLException {
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Object[] object = new Object[4];

        for (Task listTask : taskListDB.listTasks()) {
            object[0] = listTask.getName();
            object[1] = listTask.getDescription();
            object[2] = listTask.getDeadline();
            object[3] = listTask.getPriority();

            tableModel.addRow(object);
        }
        view.tasksTable.setModel(tableModel);
    }

    /*
     * Accesses the view data, retrieves it, creates a task object and inserts it
     * into the database.
     */
    private void insertNewTask(){
        // accessing view data
        String name = view.titletxt.getText();
        String description = view.descriptiontxt.getText();
        Date deadline = view.dateChooser.getDate();
        String priority = view.priorityChoice.getSelectedItem();

        // Create task and insert into database
        Task task = new Task(name,description,deadline,priority);
        taskListDB.insertNewTask(task);
    }

    // Reset the form
    private void clearForm() {
        view.titletxt.setText("");
        view.descriptiontxt.setText("");
        view.dateChooser.setDate(null);
        view.priorityChoice.select(0);
        System.out.println(" ejecutado");
    }

}
