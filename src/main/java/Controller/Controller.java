package Controller;

import Model.Task;
import Repository.TaskRepositoryImp;
import View.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
public class Controller implements ActionListener, ControllerActions {
    private static int selectedRowValue = -1;
    View view;
    TaskRepositoryImp taskListDB;
    DefaultTableModel tableModel;

    public Controller(View view) throws SQLException {
        this.view = view;
        taskListDB = new TaskRepositoryImp();
        tableModel = new DefaultTableModel();

        this.view.btnAdd.addActionListener(this);
        this.view.btnCancel.addActionListener(this);
        this.view.btnDelete.addActionListener(this);
        this.view.btnUpdate.addActionListener(this);

        listTasksAction(view.tasksTable);
        displaySelectedTaskInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addBtnAction(e);
        cancelBtnAction(e);
        updateBtnAction(e);
        deleteBtnAction(e);
    }
    /**
     * Handles the click event of the "Add" button in the GUI, calls the insertNewTaskAction() and listTasksAction() methods
     *
     * @param e checks whether the event was triggered by clicking the "Add" button.
     */
    private void addBtnAction(ActionEvent e) {
        if(e.getSource()==view.btnAdd) {
            insertNewTaskAction();
            clearForm();
            try {
                listTasksAction(view.tasksTable);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    /**
     * Handles the click event of the "Cancel" button in the GUI, calls the clearForm() and enableBtns() methods
     *
     * @param e checks whether the event was triggered by clicking the "Cancel" button.
     */
    private void cancelBtnAction(ActionEvent e){
        if(e.getSource()==view.btnCancel) {
            clearForm();

            // The status of the buttons returns to the original position.
            enableBtns(true,false);
        }

    }
    /**
     * Handles the click event of the "Update" button in the GUI, calls the updateTaskAction() and listTaskAction() methods
     *
     * @param e checks whether the event was triggered by clicking the "Cancel" button.
     */
    private void updateBtnAction(ActionEvent e){
        if(e.getSource()==view.btnUpdate) {
            updateTaskAction();
            clearForm();
            enableBtns(true,false);
        }
        try {
            listTasksAction(view.tasksTable);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Handles the click event of the "Update" button in the GUI, calls the updateTaskAction() and listTaskAction() methods
     *
     * @param e checks whether the event was triggered by clicking the "Cancel" button.
     */
    private void deleteBtnAction(ActionEvent e) {
        if(e.getSource()==view.btnDelete) deleteTaskAction();
        try {
            listTasksAction(view.tasksTable);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
    * Runs through tasks list (generated previously in TaskRepositoryImp class) and show
    * all tasks in the view
    *
    * @param table the Jtable element
    */
    @Override
    public void listTasksAction(JTable table) throws SQLException {
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Object[] object = new Object[6];

        for (Task listTask : taskListDB.listTasks()) {
            object[0] = listTask.getName();
            object[1] = listTask.getDescription();
            object[2] = listTask.getDeadline();
            object[3] = listTask.getPriority();
            object[4] = listTask.isFinished() ? "Yes":"No" ;
            object[5] = listTask.getId();

            tableModel.addRow(object);
        }
        view.tasksTable.setModel(tableModel);
    }

    /*
     * Accesses the view data, retrieves it, creates a task object and inserts it
     * into the database.
     */
    @Override
    public void insertNewTaskAction(){
        // accessing view data
        Task taskToInsert = newFormValuesTask();

        // Insert into database
        taskListDB.insertNewTask(taskToInsert);
    }

    // Update selected task
    @Override
    public void updateTaskAction(){
        // accessing view data
        Task taskToUpdate = newFormValuesTask();

        // get task id
        int id = taskListDB.listTasks().get(selectedRowValue).getId();
        
        // Update into database
        taskListDB.updateTask(taskToUpdate, id);
    }

    // removes the selected task based on id
    @Override
    public void deleteTaskAction(){
        int id = taskListDB.listTasks().get(selectedRowValue).getId();
        taskListDB.deleteTask(id);
    }

    // Reset the form
    private void clearForm() {
        view.titletxt.setText("");
        view.descriptiontxt.setText("");
        view.dateChooser.setDate(null);
        view.priorityChoice.select(0);
    }

     //When the user clicks on the task, the information of the selected task appears in the form.
    private void displaySelectedTaskInfo(){
        view.tasksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.tasksTable.getSelectedRow();

                // The static variable selectedRowValue is updated based on the selected row.
                selectedRowValue=selectedRow;

                view.titletxt.setText(view.tasksTable.getValueAt(selectedRow,0).toString());
                view.descriptiontxt.setText(view.tasksTable.getValueAt(selectedRow,1).toString());
                view.dateChooser.setDate((Date) view.tasksTable.getValueAt(selectedRow,2));
                view.priorityChoice.select(view.tasksTable.getValueAt(selectedRow,3).toString());
                view.isFinished.setSelected(view.tasksTable.getValueAt(selectedRow, 4).equals("Yes"));

                // If a task is selected, it can be edited but not added.
                enableBtns(false,true);
            }
        });

    }

    /**
     * gets the values of the form entered by the user
     *
     * @return new task
     * */
    private Task newFormValuesTask() {
            String name = view.titletxt.getText();
            String description = view.descriptiontxt.getText();
            Date deadline = view.dateChooser.getDate();
            String priority = view.priorityChoice.getSelectedItem();
            boolean isFinished = view.isFinished.isSelected();

            return new Task (name, description, deadline, priority, isFinished);
    }

    /**
     * Change the status of the buttons
     *
     * @param isAddEnable indicates if the add button can be pressed
     * @param isUpdateEnable indicates if the update button can be pressed
     * */
    private void enableBtns(boolean isAddEnable, boolean isUpdateEnable){
        view.btnAdd.setEnabled(isAddEnable);
        view.btnUpdate.setEnabled(isUpdateEnable);
    }
}
