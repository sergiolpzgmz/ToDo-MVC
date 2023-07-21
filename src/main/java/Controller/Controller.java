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
        selectTableRows(view.tasksTable);
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
        Object[] object = new Object[5];

        for (Task listTask : taskListDB.listTasks()) {
            object[0] = listTask.getName();
            object[1] = listTask.getDescription();
            object[2] = listTask.getDeadline();
            object[3] = listTask.getPriority();
            object[4] = listTask.isFinished() ? "Yes":"No" ;

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
        boolean isFinished = view.isFinished.isSelected();

        // Create task and insert into database
        Task task = new Task(name,description,deadline,priority,isFinished);
        taskListDB.insertNewTask(task);
    }

    // Reset the form
    private void clearForm() {
        view.titletxt.setText("");
        view.descriptiontxt.setText("");
        view.dateChooser.setDate(null);
        view.priorityChoice.select(0);
    }

    /**
     * When the user clicks on the task, the information of the selected task appears in the form.
     *
     * @param jTable the table showing the database information*/
    private void selectTableRows(JTable jTable){
        view.tasksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable.getSelectedRow();

                view.titletxt.setText(jTable.getValueAt(selectedRow,0).toString());
                view.descriptiontxt.setText(jTable.getValueAt(selectedRow,1).toString());
                view.dateChooser.setDate((Date) jTable.getValueAt(selectedRow,2));
                view.priorityChoice.select(jTable.getValueAt(selectedRow,3).toString());
                view.isFinished.setSelected(jTable.getValueAt(selectedRow, 4).equals("Yes"));
            }
        });
    }


}
